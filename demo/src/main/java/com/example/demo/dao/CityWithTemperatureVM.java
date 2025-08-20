/*
 * Copyright (c) 2025 Mavenir Systems, Inc. All rights reserved.
 * This source code is the property of Mavenir Systems, Inc.
 * Unauthorized copying or distribution of this file, via any medium is strictly prohibited.
 */
package com.example.demo.dao;

import com.example.demo.entity.City;
import com.example.demo.entity.Temperature;

import java.util.List;

/***
 * @author Aditya Patil
 * @date 18-08-2025
 */
public class CityWithTemperatureVM {

    private City city;

    private List<Temperature> temperatureList;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Temperature> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(List<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }
}
