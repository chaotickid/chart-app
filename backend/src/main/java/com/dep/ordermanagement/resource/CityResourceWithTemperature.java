/*
 * Copyright (c) 2025 Mavenir Systems, Inc. All rights reserved.
 * This source code is the property of Mavenir Systems, Inc.
 * Unauthorized copying or distribution of this file, via any medium is strictly prohibited.
 */
package com.dep.ordermanagement.resource;

import com.dep.ordermanagement.dao.CityVM;
import com.dep.ordermanagement.dao.CityWithTemperatureData;
import com.dep.ordermanagement.dao.CityWithTemperatureVM;
import com.dep.ordermanagement.dao.TemperatureVM;
import com.dep.ordermanagement.entity.City;
import com.dep.ordermanagement.entity.Temperature;
import com.dep.ordermanagement.repository.CityRepo;
import com.dep.ordermanagement.repository.TemperatureRepo;
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

        CityVM cityVM = cityWithTemperatureVM.getCity();
        String icity = cityVM.getName();
        String state = cityVM.getState();
        String country = cityVM.getCountry();
        City oldCity = null;
        City citySaveToDb = new City();
        City city = null;

        try {
            oldCity = cityRepo.findCityByNameAndStateAndCountry(icity, state, country).orElseThrow(() ->
                    new RuntimeException("city not found"));//db
        } catch (Exception e) {
            oldCity = null;
        }

        if(oldCity != null){
            citySaveToDb = oldCity;
        }




        if(oldCity== null) {
            citySaveToDb.setCountry(cityVM.getCountry());
            citySaveToDb.setName(cityVM.getName());
            citySaveToDb.setState(cityVM.getState());
            city = cityRepo.save(citySaveToDb);//save que
        }else{
            city = cityRepo.save(citySaveToDb);//save que
        }

        //City city = cityRepo.save(citySaveToDb);//save que


        for (TemperatureVM t : cityWithTemperatureVM.getTemperatureList()) {
            Temperature saveToDb = new Temperature();

            saveToDb.setMax(t.getMax());
            saveToDb.setMin(t.getMin());
            saveToDb.setAvg(t.getAvg());
            saveToDb.setDate(t.getDate());

            temperatureRepo.save(saveToDb);
            city.addTemperature(saveToDb);//mapping
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
        System.out.println(cityWithTemperatureData);

        return new ResponseEntity<>(cityWithTemperatureData, HttpStatus.OK);

    }
}
