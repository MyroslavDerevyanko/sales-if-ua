package sales.users.service;

import sales.users.domain.City;
import sales.users.domain.Region;

import java.util.List;

/**
 * Created by taras on 04.08.15.
 */
public interface CityService {
    List<City> getAll(Region region);

    City getOne(Region region, Long id);

    City getOneByName(String name);
}
