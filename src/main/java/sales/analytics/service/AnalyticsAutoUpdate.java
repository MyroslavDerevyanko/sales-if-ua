package sales.analytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import sales.analytics.domain.SalesAnalytic;
import sales.analytics.domain.ShopsAnalytic;
import sales.analytics.repository.ClientsAnalyticsRepository;
import sales.analytics.repository.SalesAnalyticsRepository;

import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sales.analytics.repository.ShopsAnalyticsRepository;
import sales.users.repository.UserRepository;

/**
 * Created by Myroslav on 30.07.2015.
 */
public class AnalyticsAutoUpdate {
    @Autowired
    private AnalyticsService analyticsService;
    @Autowired
    private ShopsAnalyticsRepository shopsAnalyticsRepository;

    protected static Logger logger = LoggerFactory.getLogger(AnalyticsAutoUpdate.class.getName());

    @Scheduled(cron="50 23 */1 * * ?")
    public void dailyUpdate()
    {
        Random rand = new Random();
        logger.debug("Daily update" + new Date());
        shopsAnalyticsRepository.save(new ShopsAnalytic(rand.nextInt(20)+analyticsService.getUsersAmountForLastTime(24*60), new Date()));
    }



}
