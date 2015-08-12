package sales.analytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import sales.analytics.domain.ClientsAnalytic;
import sales.analytics.domain.SalesAnalytic;
import sales.analytics.domain.ShopsAnalytic;
import sales.analytics.repository.ClientsAnalyticsRepository;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sales.analytics.repository.SalesAnalyticsRepository;
import sales.analytics.repository.ShopsAnalyticsRepository;
import sales.orders.services.OrdersService;
import sales.users.domain.User;

/**
 * Created by Myroslav on 30.07.2015.
 */
public class AnalyticsAutoUpdate {
    @Autowired
    private AnalyticsService analyticsService;
    @Autowired
    private ShopsAnalyticsRepository shopsAnalyticsRepository;

    @Autowired
    private ClientsAnalyticsRepository clientsAnalyticsRepository;

    @Autowired
    private SalesAnalyticsRepository salesAnalyticsRepository;

    @Autowired
    private OrdersService ordersService;

    protected static Logger logger = LoggerFactory.getLogger(AnalyticsAutoUpdate.class.getName());

    @Scheduled(cron = "50 23 23 */1 * ?")
    public void dailyUpdate() {
        Random rand = new Random();
        logger.debug("Daily update" + new Date());
        clientsAnalyticsRepository.save(new ClientsAnalytic(rand.nextInt(40) + analyticsService.getShopsAmountForLastTime(24 * 60), new Date()));
        shopsAnalyticsRepository.save(new ShopsAnalytic(rand.nextInt(20) + analyticsService.getClientsAmountForLastTime(24 * 60), new Date()));
        for (User shop : analyticsService.getAllShops()) {

        }
    }


}
