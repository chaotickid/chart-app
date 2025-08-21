/**
 * Copyright © 2025 Mavenir Systems
 */
package com.dep.ordermanagement.repository;


import com.dep.ordermanagement.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/***
 * @author Aditya Patil
 * @date 18-08-2025
 */
@Repository
public interface TemperatureRepo extends JpaRepository<Temperature, Integer> {
}
