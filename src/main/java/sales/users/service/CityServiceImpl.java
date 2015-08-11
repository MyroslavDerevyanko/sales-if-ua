package sales.users.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sales.users.domain.City;
import sales.users.domain.Region;

import sales.users.repository.CityRepository;

import java.util.List;

/**
 * Created by taras on 04.08.15.
 */
@Service("cityService")
public class CityServiceImpl implements CityService{
    final static Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAll(Region region) {
        logger.debug("Get all cities");
        return cityRepository.findByRegion(region);
    }

    @Override
    public City getOne(Region region, Long id) {
        logger.debug("Get city by id");
        return cityRepository.findByRegionAndId(region, id);
    }

    @Override
    public City getOneByName(String name) {
        logger.debug("Get city by name");
        return cityRepository.findByName(name);
    }
}
