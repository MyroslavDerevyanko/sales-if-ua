package sales.analytics.service;

import sales.analytics.domain.Analytics;
import sales.orders.domain.Order;
import sales.users.domain.User;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Myroslav on 29.07.2015.
 */
public interface AnalyticsService {
    public Analytics get(GregorianCalendar date);

    public List<Analytics> getAll();

    public Analytics save(Analytics analytics);

    public List<Analytics> getByPeriod(Date from, Date to);

    public List<Analytics> getAfter(Date date);

    public List<Analytics> getBefore(Date date);

    public List<User> getUsersForLastTime(String user, int min);

    public int getUsersAmountForLAstTime(String user, int min);

    public double getMoneyTransactionForLastTime(int h);

    public int getSoldGoods(int h);

  /*  public void AnalyticsAutoUpdate();*/

}
