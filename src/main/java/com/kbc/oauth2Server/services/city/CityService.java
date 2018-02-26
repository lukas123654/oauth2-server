package com.kbc.oauth2Server.services.city;

import com.kbc.oauth2Server.model.domain.City;

import java.util.List;

public interface CityService {

    City createNewCity(String name, String state);

    List<City> findAllCities();

    City findCityById(Long id);

    City findCityByName(String name);

    List<City> findCityContainsName(String name);

    void deleteCityById(Long id);

    Long deleteCityByName(String name);

}
