package com.kbc.oauth2Server.model.repository;

import com.kbc.oauth2Server.model.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);

    List<City> findByNameContainingIgnoreCase(String name);

    @Transactional
    Long deleteByName(String name);

}
