package sales.payment.paymentHandler;

import com.paypal.api.payments.CreditCard;
import com.paypal.core.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sales.orders.domain.Order;
import sales.orders.services.OrdersService;
import sales.payment.creaditCard.service.CreaditCardService;
import sales.payment.dto.data.AnonymMultyPaymentDTO;
import sales.payment.dto.data.RegisteredMultiPaymentDTO;
import sales.payment.paypal.service.Authentication;
import sales.payment.paypal.service.PayPalPayment;
import sales.payment.paypal.service.PaypalService;
import sales.roles.service.RoleService;
import sales.storage.domain.Storage;
import sales.storage.service.StorageService;
import sales.users.domain.User;
import sales.users.service.UserService;
import sales.util.Constants;

import java.util.*;

/**
 * Created by volodya on 10.08.15.
 */
@Component
public class RegistretedPaymentHandler {

    @Autowired
    private StorageService storageService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CreaditCardService creaditCardService;

    @Autowired
    UserService userService;

    protected static Logger logger = LoggerFactory.getLogger(RegistretedPaymentHandler.class.getName());

    private CreditCard card;

    private Set<User> users;

    private List<Storage> storages;

    private List<Transaction> transactions;

    private void setCard(sales.payment.creaditCard.domain.CreditCard card) {
        this.card = new CreditCard();
        this.card.setNumber(card.getNumber());
        this.card.setType(card.getType());
        this.card.setExpireMonth(card.getExpireMonth());
        this.card.setExpireYear(card.getExpireYear());
        this.card.setNumber(card.getNumber());
        this.card.setCvv2(card.getCvv2());
    }

    private void setCard(long creditCardId) {
        sales.payment.creaditCard.domain.CreditCard card = creaditCardService.get(creditCardId);
        this.card = new CreditCard();
        this.card.setNumber(card.getNumber());
        this.card.setType(card.getType());
        this.card.setExpireMonth(card.getExpireMonth());
        this.card.setExpireYear(card.getExpireYear());
        this.card.setNumber(card.getNumber());
        this.card.setCvv2(card.getCvv2());
    }

    public CreditCard getCard() {
        return card;
    }

    private void getUsersByGoodsId(List<Long> ids) {
        users = new HashSet<User>();
        for (Long id : ids) {
            users.add(storageService.get(id).getUser());
        }
    }

    private void getGoods(List<Long> ids) {
        storages = new ArrayList<Storage>();
        for (Long id : ids) {
            storages.add(storageService.get(id));
        }
    }

    private void defineTransactions(CreditCard card) {
        transactions = new ArrayList<Transaction>();
        for (User user : this.users) {
            Transaction transaction = new Transaction();
            for (Storage storage : this.storages) {
                if (user.equals(storage.getUser())) {
                    transaction.getStorages().add(storage);
                }
            }
            transaction.setPaypal(paypalService.get(user.getId()));
            transaction.setCard(card);
            transactions.add(transaction);
        }
    }

    public void anonymPayment(RegisteredMultiPaymentDTO paymentDTO) throws PayPalRESTException {
        this.preparePayment(paymentDTO);
        this.callPayPal();
        this.saveOrders(paymentDTO.getUserId(), this.transactions);
    }

    private void preparePayment(RegisteredMultiPaymentDTO paymentDTO) {
        if(paymentDTO.getCard().equals(null)){setCard(paymentDTO.getCard());}
        else{ setCard(paymentDTO.getCardId());}
        this.getUsersByGoodsId(paymentDTO.getGoodsId());
        this.getGoods(paymentDTO.getGoodsId());
        defineTransactions(this.card);
    }

    private void callPayPal() throws PayPalRESTException {
        for (Transaction transaction : this.transactions) {
            PayPalPayment payment = new PayPalPayment(Authentication.
                    getAuthenticationToken(transaction.getPaypal().getClientId(),
                            transaction.getPaypal().getSecret()));
            payment.prepareCard(transaction.getCard());
            payment.prepareTransaction(transaction.getTotalAmont());
            transaction.setPaymentId(payment.makePayment());
        }
    }

    private User createUser(AnonymMultyPaymentDTO paymentDTO) {
        User user = new User();
        user.setEmail(paymentDTO.getEmail());
        user.setFirstName(paymentDTO.getFirstName());
        user.setLastName(paymentDTO.getLastName());
        user.setDate(new Date());
        user.setRole(roleService.getRoleByValue(Constants.CLIENT));
        return user;
    }

    private void saveOrders(long userId, List<Transaction> transactions) {
        User user = userService.getById(userId);
        for (Transaction transaction : transactions) {
            saveTransaction(user, transaction.getStorages(), transaction.getPaymentId());
        }
    }

    private void saveTransaction(User user, List<Storage> storages, String paymentId) {
        for (Storage storage : storages) {
            Order order = new Order(user, storage, paymentId);
            ordersService.save(order);
        }
    }
}
