package sales.payment.creaditCard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.payment.creaditCard.domain.CreditCard;
import sales.payment.creaditCard.repository.CreditCardRepository;

import java.util.List;

/**
 * Created by volodya on 29.07.15.
 */
@Service("creditCardService")
@Transactional
public class CreditCardServiceImpl implements CreaditCardService{

    @Autowired
    private CreditCardRepository repository;

    @Override
    public CreditCard get(long id) {
        return repository.findById(id);
    }

    @Override
    public List<CreditCard> getAll() {
        return repository.findAll();
    }

    @Override
    public CreditCard save(CreditCard card) {
        return repository.save(card);
    }

    @Override
    public void delete(long id) {
        repository.removeById(id);
    }
}
