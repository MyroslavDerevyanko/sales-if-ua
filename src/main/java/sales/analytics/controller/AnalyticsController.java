package sales.analytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sales.analytics.domain.Analytics;
import sales.analytics.service.AnalyticsAutoUpdate;
import sales.analytics.service.AnalyticsService;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Myroslav on 29.07.2015.
 */

@RestController
@RequestMapping("/analytics")

public class AnalyticsController {

    protected static Logger logger = Logger.getLogger(AnalyticsController.class.getName());

    @Autowired
    private AnalyticsService analyticsService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")

    public List<Analytics> getAll()
    {
        logger.info("Get all analytics");
        return analyticsService.getAll();
    }

    @RequestMapping(value = "/{day}/{month}/{year}", method = RequestMethod.GET, produces = "application/json")

    public Analytics getByDate(@PathVariable int day, @PathVariable int month, @PathVariable int year)
    {
        return analyticsService.get(new GregorianCalendar(day,month,year));
    }

}

