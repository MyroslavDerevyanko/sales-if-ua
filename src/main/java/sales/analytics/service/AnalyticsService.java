package sales.analytics.service;

import sales.analytics.domain.ClientsAnalytic;
import sales.analytics.domain.SalesAnalytic;
import sales.analytics.domain.ShopsAnalytic;
import sales.users.domain.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Myroslav on 29.07.2015.
 */
public interface AnalyticsService {
    public List<ClientsAnalytic> getAllClientAnalytic();

    public List<ShopsAnalytic> getAllShopsAnalytic();

    public int getClientsAmountForLastTime(int min);

    public int getShopsAmountForLastTime(int min);

    public List<SalesAnalytic> getAllSales();

    public List<User> getLastUsers(int min);

    public List<User> getAllShops();

    public List<SalesAnalytic> getAnalyticsByShop(Long shop);
}
