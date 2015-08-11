package sales.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.analytics.domain.ClientsAnalytic;

/**
 * Created by Myroslav on 11.08.2015.
 */
public interface ClientsAnalyticsRepository extends JpaRepository<ClientsAnalytic, Long> {
}
