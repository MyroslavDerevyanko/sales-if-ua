package sales.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.users.domain.City;
import sales.users.domain.Region;

import java.util.List;

/**
 * Created by taras on 04.08.15.
 */
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByRegion(Region region);

    City findByRegionAndId(Region region, Long id);

    City findByName(String name);
}
