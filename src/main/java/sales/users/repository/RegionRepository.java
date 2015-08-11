package sales.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.users.domain.Country;
import sales.users.domain.Region;

import java.util.List;

/**
 * Created by taras on 04.08.15.
 */
public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region> findByCountry(Country country);

    Region findByCountryAndId(Country country, Long id);

    Region findByName(String name);
}
