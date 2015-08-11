package sales.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.analytics.domain.SalesAnalytic;

import java.util.Date;
import java.util.List;

/**
 * Created by Myroslav on 29.07.2015.
 */
public interface SalesAnalyticsRepository extends JpaRepository<SalesAnalytic, Integer>{
    SalesAnalytic findByDate(Date date);
    List<SalesAnalytic> findByDateBetween(Date from, Date to);
    List<SalesAnalytic> findByDateAfter(Date date);
    List<SalesAnalytic> findByDateBefore(Date date);
}
