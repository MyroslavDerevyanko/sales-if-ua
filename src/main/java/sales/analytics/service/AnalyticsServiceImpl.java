package sales.analytics.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sales.analytics.domain.ClientsAnalytic;
import sales.analytics.domain.SalesAnalytic;
import sales.analytics.domain.ShopsAnalytic;
import sales.analytics.repository.ClientsAnalyticsRepository;
import sales.analytics.repository.SalesAnalyticsRepository;
import sales.analytics.repository.ShopsAnalyticsRepository;
import sales.roles.service.RoleService;
import sales.users.domain.User;
import sales.users.service.UserService;
import sales.util.Constants;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Myroslav on 28.07.2015.
 */

@Service
@Transactional

public class AnalyticsServiceImpl implements AnalyticsService {
    @Autowired
    private ClientsAnalyticsRepository clientsAnalyticsRepository;

    @Autowired
    private ShopsAnalyticsRepository shopsAnalyticsRepository;

    @Autowired
    private SalesAnalyticsRepository salesAnalyticsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(AnalyticsServiceImpl.class.getName());

    @Override
    public List<ClientsAnalytic> getAllClientAnalytic() {
        if (IsTableClientsAnalyticsEmpty()) {
            clientsTableAutoGenerate();
        }
        return clientsAnalyticsRepository.findAll();
    }

    @Override
    public List<ClientsAnalytic> getClientsAnalyticForPeriod(Date from, Date to) {
        return clientsAnalyticsRepository.findByDateBetween(from, to);
    }

    @Override
    public List<ShopsAnalytic> getAllShopsAnalytic() {
        if (IsTableShopsAnalyticsEmpty()) {
            shopsTableAutoGenerate();
        }
        return shopsAnalyticsRepository.findAll();
    }

    @Override
    public List<ShopsAnalytic> getShopsAnalyticForPeriod(Date from, Date to) {
        return shopsAnalyticsRepository.findByDateBetween(from, to);
    }

    @Override
    public int getClientsAmountForLastTime(int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -min);
        return userService.findByCreationDateAfterAndRole(calendar.getTime(), roleService.getRoleByValue(Constants.CLIENT)).size();
    }

    @Override
    public int getShopsAmountForLastTime(int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -min);
        return userService.findByCreationDateAfterAndRole(calendar.getTime(), roleService.getRoleByValue(Constants.SHOP)).size();
    }

    @Override
    public List<SalesAnalytic> getAllSales() {
        if (IsTableSalesAnalyticsEmpty()) {
            salesTableAutoGenerate();
        }
        return salesAnalyticsRepository.findAll();
    }

    @Override
    public List<User> getLastUsers(int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -min);
        return userService.findByCreationDateAfter(calendar.getTime());
    }

    @Override
    public List<User> getAllShops() {
        return userService.findByRole(roleService.getRoleByValue(Constants.SHOP));
    }

    @Override
    public List<SalesAnalytic> getAnalyticsByShop(Long shopId) {
        if (IsTableSalesAnalyticsEmpty()) {
            salesTableAutoGenerate();
        }
        return salesAnalyticsRepository.findByShop(userService.getById(shopId));
    }

    @Override
    public List<SalesAnalytic> getAnalyticsByShopForPeriod(Long shopId, Date from, Date to) {
        if (IsTableSalesAnalyticsEmpty()) {
            salesTableAutoGenerate();
        }
        return salesAnalyticsRepository.findByShopAndDateBetween(userService.getById(shopId), from, to);
    }

    public void clientsTableAutoGenerate() {
        logger.info("Clients analytics is not found. Generating...");
        for (int i = 180; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            Random rand = new Random();
            clientsAnalyticsRepository.save(new ClientsAnalytic(10 + rand.nextInt(20), calendar.getTime()));
        }
    }

    public void shopsTableAutoGenerate() {
        logger.info("Shops analytics is not found. Generating...");
        for (int i = 180; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -i);
            Random rand = new Random();
            shopsAnalyticsRepository.save(new ShopsAnalytic(10 + rand.nextInt(10), calendar.getTime()));
        }
    }

    public void salesTableAutoGenerate() {
        logger.info("Sales analytics is not found. Generating...");

        for (int i = 180; i > 0; i--) {
            for (User shop : userService.findByRole(roleService.getRoleByValue(Constants.SHOP))) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -i);
                Random rand = new Random();
                salesAnalyticsRepository.save(new SalesAnalytic(shop, 10 + rand.nextInt(50), 10000 + rand.nextInt(5000), calendar.getTime()));
            }
        }

    }

    public boolean IsTableClientsAnalyticsEmpty() {
        if (clientsAnalyticsRepository.findAll().size() == 0) {
            logger.info("Table clientsAnalytic is empty");
            return true;
        }
        return false;
    }

    public boolean IsTableShopsAnalyticsEmpty() {
        if (shopsAnalyticsRepository.findAll().size() == 0) {
            logger.info("Table shopsAnalytics is empty");
            return true;
        }
        return false;
    }

    public boolean IsTableSalesAnalyticsEmpty() {
        if (salesAnalyticsRepository.findAll().size() == 0) {
            logger.info("Table salesAnalytic is empty");
            return true;
        }
        return false;
    }

}
