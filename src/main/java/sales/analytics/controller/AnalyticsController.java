package sales.analytics.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sales.analytics.domain.ClientsAnalytic;
import sales.analytics.domain.SalesAnalytic;
import sales.analytics.domain.ShopsAnalytic;
import sales.analytics.service.AnalyticsService;
import sales.users.domain.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Myroslav on 29.07.2015.
 */
@Api(basePath = "/analytics", value = "analytics", description = "Endpoint for analytics management")
@RestController
@RequestMapping("/analytics")

public class AnalyticsController {

    protected static Logger logger = LoggerFactory.getLogger(AnalyticsController.class.getName());

    @Autowired
    private AnalyticsService analyticsService;

    @RequestMapping(value = "/admin/clients", method = RequestMethod.GET, produces = "application/json")

    public List<ClientsAnalytic> getAllClientsAnalytics() {
        logger.info("Get all clients analytics");
        return analyticsService.getAllClientAnalytic();
    }

    @RequestMapping(value = "/admin/shops", method = RequestMethod.GET, produces = "application/json")

    public List<ShopsAnalytic> getAllShopsAnalytics()
    {
        logger.info("Get all shops analytics");
        return analyticsService.getAllShopsAnalytic();
    }

    /**
     *
     * @param from - from some date in ms (example - 1431593292000)
     * @param to - to some date in ms
     * @return List of analitics fo some period
     */
   /* @ApiOperation(httpMethod = "GET", value = "Get analytics for some period", notes = "Return analytics from some date to some date", response = ShopsAnalytic.class, responseContainer="List")

    @RequestMapping(value = "/admin/byPeriod", method = RequestMethod.GET, produces = "application/json")

    public List<SalesAnalytic> getByPeriod(@ApiParam(value = "long from - select analytics from some date in ms", required = true)@RequestParam(value = "from") long from, @ApiParam(value = "long to - select analytics to some date in ms", required = true)@RequestParam(value = "to") long to)
    {
        Date fromDate = new Date(from);
        Date toDate = new Date(to);
        logger.info("Get all analytics from " + fromDate + "to" + toDate);
        return analyticsService.getByPeriod(fromDate, toDate);
    }

    @ApiOperation(httpMethod = "GET", value = "Get analytics after some date", notes = "Return analytics after some date", response = ShopsAnalytic.class, responseContainer="List")

    @RequestMapping(value = "admin/after/{date}", method = RequestMethod.GET, produces = "application/json")

    public List<SalesAnalytic> getAfter(@ApiParam(value = "long date - some date in ms", required = true)@PathVariable long date)
    {
        Date afterDate = new Date(date);
        logger.info("Get analytics after " + afterDate);
        return analyticsService.getAfter(afterDate);
    }

    @ApiOperation(httpMethod = "GET", value = "Get analytics before some date", notes = "Return analytics before some date", response = ShopsAnalytic.class, responseContainer="List")

    @RequestMapping(value = "admin/before/{date}", method = RequestMethod.GET, produces = "application/json")

    public List<SalesAnalytic> getBefore(@ApiParam(value = "long date - some date in ms", required = true)@PathVariable long date)
    {
        Date beforeDate = new Date(date);
        logger.info("Get analytics before " + beforeDate);
        return analyticsService.getBefore(beforeDate);
    }

    @RequestMapping(value = "admin/lastUsers/{user}/{min}", method = RequestMethod.GET, produces = "application/json")

    public List<User> getLastUsers(@PathVariable String user, @PathVariable int min)
    {
        logger.info("Get new "+user+" for last "+min + " min");
        return analyticsService.getUsersForLastTime(user, min);
    }

    @RequestMapping(value = "admin/lastUsersAmount/{user}/{min}", method = RequestMethod.GET, produces = "application/json")

    public int getLastUsersAmount(@PathVariable String user, @PathVariable int min)
    {
        logger.info("Get new" +  user + " amount for last" + min + "min");
        return analyticsService.getUsersAmountForLAstTime(user, min);
    }

    @RequestMapping(value = "lastOrders/{h}", method = RequestMethod.GET, produces = "application/json")

    public double getMoneyTransaction(@PathVariable int h)
    {
        logger.info("Get money transaction for last " + h + " h");
        return analyticsService.getMoneyTransactionForLastTime(h);
    }

    @RequestMapping(value = "soldGoods/{h}", method = RequestMethod.GET, produces = "application/json")

    public int getSoldGoods(@PathVariable int h)
    {
        logger.info("Get sold goods amount for last " + h + " h");
        return analyticsService.getSoldGoods(h);
    }
*/
}

