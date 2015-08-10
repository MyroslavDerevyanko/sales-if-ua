package sales.analytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sales.analytics.domain.Analytics;
import sales.analytics.repository.AnalyticsRepository;
import sales.orders.domain.Order;
import sales.orders.services.OrdersService;
import sales.roles.service.RoleService;
import sales.users.domain.User;
import sales.users.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Myroslav on 28.07.2015.
 */

@Service
@Transactional

public class AnalyticsServiceImpl implements AnalyticsService{

    protected static Logger logger = Logger.getLogger(AnalyticsServiceImpl.class.getName());;
    @Autowired
    private AnalyticsRepository analyticsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrdersService ordersService;

    @Override
    public Analytics get(GregorianCalendar date) {
        return analyticsRepository.findByDate(date);
    }

    @Override
    public List<Analytics> getAll() {
        if(tableIsEmpty()) {
            createOnStart();
        }
        return analyticsRepository.findAll();
    }

    @Override
    public Analytics save(Analytics analytics) {
        analyticsRepository.save(analytics);
        return analytics;
    }

    @Override
    public List<Analytics> getByPeriod(Date from, Date to) {
        return analyticsRepository.findByDateBetween(from, to);
    }

    @Override
    public List<Analytics> getAfter(Date date) {
        return analyticsRepository.findByDateAfter(date);
    }

    @Override
    public List<Analytics> getBefore(Date date) {
        return analyticsRepository.findByDateBefore(date);
    }

    @Override
    public List<User> getUsersForLastTime(String user, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -min);
        return  userRepository.findByCreationDateAfterAndRole(calendar.getTime(), roleService.getRoleByValue(user));
        //return  userRepository.findByCreationDateAfter(calendar.getTime());

    }

    @Override
    public int getUsersAmountForLAstTime(String user, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -min);
        return  userRepository.findByCreationDateAfterAndRole(calendar.getTime(), roleService.getRoleByValue(user)).size();
    }

    @Override
    public double getMoneyTransactionForLastTime(int h) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -h);
        List<Order> orders = ordersService.getByDateAfter(calendar.getTime());
        double sum=0;
        for(Order order: orders)
        {
            sum+=order.getStorage().getPrice();
        }
        return sum;
    }

    @Override
    public int getSoldGoods(int h) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -h);
        List<Order> orders = ordersService.getByDateAfter(calendar.getTime());
        return orders.size();
    }


    public void createOnStart()
    {
        for(int i=180; i>0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            Random rand = new Random();
            analyticsRepository.save(new Analytics(100+rand.nextInt(500), 20000+rand.nextInt(980000), 100 + rand.nextInt(700), 15+rand.nextInt(85), calendar.getTime()));
            logger.info("Fake date added");
        }
    }

    public boolean tableIsEmpty()
    {
        if(analyticsRepository.findAll().size()==0)
        {
            logger.info("Table is empty");
            return true;
        }
        return false;
    }

}
