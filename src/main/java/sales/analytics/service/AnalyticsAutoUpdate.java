package sales.analytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sales.analytics.domain.Analytics;
import sales.analytics.repository.AnalyticsRepository;

import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Myroslav on 30.07.2015.
 */
public class AnalyticsAutoUpdate {
    protected static Logger logger = LoggerFactory.getLogger(AnalyticsAutoUpdate.class.getName());;
    @Autowired
    private AnalyticsService analyticsService;
    @Autowired
    private AnalyticsRepository analyticsRepository;

    @Scheduled(cron="50 23 */1 * * ?")
    public void dailyUpdate()
    {
        Random rand = new Random();
        logger.debug("Daily update" + new Date() + " new clients - " + analyticsService.getUsersAmountForLAstTime("client", 60 * 24) + ", new shops - " + analyticsService.getUsersAmountForLAstTime("shop", 60 * 24)+ " money transaction - " + analyticsService.getMoneyTransactionForLastTime(24) + "sold goods - " + analyticsService.getSoldGoods(24));
        analyticsRepository.save(new Analytics(100 + rand.nextInt(500)+analyticsService.getSoldGoods(24), 20000 + rand.nextInt(980000)+analyticsService.getMoneyTransactionForLastTime(24), 100 + rand.nextInt(700) + analyticsService.getUsersAmountForLAstTime("client", 60 * 24), 15 + rand.nextInt(85) + analyticsService.getUsersAmountForLAstTime("shop", 60 * 24), new Date()));
    }



}
