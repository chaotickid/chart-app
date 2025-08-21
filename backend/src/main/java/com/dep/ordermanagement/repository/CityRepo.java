/**
 * Copyright © 2025 Mavenir Systems
 */
package com.dep.ordermanagement.repository;

import com.dep.ordermanagement.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 * @author Aditya Patil
 * @date 18-08-2025
 */
@Repository
public interface CityRepo extends JpaRepository<City, Integer> {

    Optional<City> findCityByNameAndStateAndCountry(String name, String state, String country);//select * from City where name = {name}
}
