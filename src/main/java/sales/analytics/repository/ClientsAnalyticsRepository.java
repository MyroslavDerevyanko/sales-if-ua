package sales.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.analytics.domain.ClientsAnalytic;

import java.util.Date;
import java.util.List;

/**
 * Created by Myroslav on 11.08.2015.
 */
public interface ClientsAnalyticsRepository extends JpaRepository<ClientsAnalytic, Long> {
    public List<ClientsAnalytic> findByDateBetween(Date from, Date to);
}
