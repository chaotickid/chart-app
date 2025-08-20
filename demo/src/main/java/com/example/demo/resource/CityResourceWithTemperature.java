/*
 * Copyright (c) 2025 Mavenir Systems, Inc. All rights reserved.
 * This source code is the property of Mavenir Systems, Inc.
 * Unauthorized copying or distribution of this file, via any medium is strictly prohibited.
 */
package com.example.demo.resource;

import com.example.demo.dao.CityVM;
import com.example.demo.dao.CityWithTemperatureData;
import com.example.demo.dao.CityWithTemperatureVM;
import com.example.demo.dao.TemperatureVM;
import com.example.demo.entity.City;
import com.example.demo.entity.Temperature;
import com.example.demo.repository.CityRepo;
import com.example.demo.repository.TemperatureRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/***
 * @author Aditya Patil
 * @date 18-08-2025
 */

@RestController
@RequestMapping("/api/v1")
@Transactional
public class CityResourceWithTemperature {

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private TemperatureRepo temperatureRepo;

    @PostMapping("/add")
    public ResponseEntity<?> addCityWithTemperature(@RequestBody CityWithTemperatureVM cityWithTemperatureVM){

        City city = cityRepo.save(cityWithTemperatureVM.getCity());//save que


        for (Temperature t : cityWithTemperatureVM.getTemperatureList()) {
            temperatureRepo.save(t);
            city.addTemperature(t);//mapping
        }

        return new ResponseEntity<>(cityWithTemperatureVM, HttpStatus.CREATED);

    }

    @GetMapping("/get")
    public ResponseEntity<?> getCity(@RequestParam (name = "city") String city,
                                     @RequestParam (name = "state") String state,
                                     @RequestParam (name = "country") String country){
        CityWithTemperatureData cityWithTemperatureData = new CityWithTemperatureData();
        CityVM cityVM = new CityVM();
        List<TemperatureVM> temperatureVM = new ArrayList<>();

        City city1 = cityRepo.findCityByNameAndStateAndCountry(city, state, country).orElseThrow(() -> new RuntimeException("city not found"));//db




















        cityVM.setCountry(city1.getCountry());
        cityVM.setName(city1.getName());
        cityVM.setState(city1.getState());
        cityWithTemperatureData.setCity(cityVM);

        for (Temperature t : city1.getTemperatureList()) {
            TemperatureVM temperatureVM1 = new TemperatureVM();
            temperatureVM1.setAvg(t.getAvg());
            temperatureVM1.setDate(t.getDate());
            temperatureVM1.setMin(t.getMin());
            temperatureVM1.setMax(t.getMax());
            temperatureVM.add(temperatureVM1);

        }
        cityWithTemperatureData.setTemperatureList(temperatureVM);

        return new ResponseEntity<>(cityWithTemperatureData, HttpStatus.OK);

    }
}
