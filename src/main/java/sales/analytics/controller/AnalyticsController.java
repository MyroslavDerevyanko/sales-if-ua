package sales.analytics.controller;

import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sales.analytics.domain.ClientsAnalytic;
import sales.analytics.domain.SalesAnalytic;
import sales.analytics.domain.ShopsAnalytic;
import sales.analytics.service.AnalyticsService;
import sales.users.domain.User;

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

    @RequestMapping(value = "/admin/usersForLast/{min}", method = RequestMethod.GET, produces = "application/json")

    public List<User> getLastUsers(@PathVariable int min)
    {
        logger.info("Get users registred for the last time");
        return analyticsService.getLastUsers(min);
    }

    @RequestMapping(value = "/sales", method = RequestMethod.GET, produces = "application/json")

    public List<SalesAnalytic> getAll()
    {
        logger.info("Get all sales");
        return analyticsService.getAllSales();
    }

    @RequestMapping(value = "sales/shop/{id}", method = RequestMethod.GET, produces = "application/json")

    public List<SalesAnalytic> getSalesByShop(@PathVariable Long id)
    {
        logger.info("Get sales analytics by shop");
        return analyticsService.getAnalyticsByShop(id);
    }
}

