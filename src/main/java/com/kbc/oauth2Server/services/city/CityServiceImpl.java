package com.kbc.oauth2Server.services.city;

import com.kbc.oauth2Server.exceptions.JpaCreateException;
import com.kbc.oauth2Server.model.domain.City;
import com.kbc.oauth2Server.model.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional
    public City createNewCity(String name, String state) {
        City city;
        try {
            city = cityRepository.saveAndFlush(new City(name, state));
        }
        catch (DataIntegrityViolationException e) {
            throw new JpaCreateException("City with given name already exists", e);
        }
        return city;
    }

    @Override
    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City findCityById(Long id) {
        return cityRepository.findOne(id);
    }

    @Override
    public City findCityByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public List<City> findCityContainsName(String name) {
        return cityRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional
    public void deleteCityById(Long id) {
        cityRepository.delete(id);
    }

    @Override
    @Transactional
    public Long deleteCityByName(String name) {
        return cityRepository.deleteByName(name);
    }
}
