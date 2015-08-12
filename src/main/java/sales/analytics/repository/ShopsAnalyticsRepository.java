package sales.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.analytics.domain.SalesAnalytic;
import sales.analytics.domain.ShopsAnalytic;

import java.util.Date;
import java.util.List;

/**
 * Created by Myroslav on 10.08.2015.
 */
public interface ShopsAnalyticsRepository extends JpaRepository<ShopsAnalytic, Long> {
    List<ShopsAnalytic> findByDateBetween(Date from, Date to);
}
