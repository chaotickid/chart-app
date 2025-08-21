/*
 * Copyright (c) 2025 Mavenir Systems, Inc. All rights reserved.
 * This source code is the property of Mavenir Systems, Inc.
 * Unauthorized copying or distribution of this file, via any medium is strictly prohibited.
 */
package com.dep.ordermanagement.dao;


import java.util.List;

/***
 * @author Aditya Patil
 * @date 18-08-2025
 */
public class CityWithTemperatureVM {

    private CityVM city;

    private List<TemperatureVM> temperatureList;


    public CityVM getCity() {
        return city;
    }

    public void setCity(CityVM city) {
        this.city = city;
    }

    public List<TemperatureVM> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(List<TemperatureVM> temperatureList) {
        this.temperatureList = temperatureList;
    }
}
