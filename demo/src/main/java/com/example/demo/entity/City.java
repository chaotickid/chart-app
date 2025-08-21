/*
 * Copyright (c) 2025 Mavenir Systems, Inc. All rights reserved.
 * This source code is the property of Mavenir Systems, Inc.
 * Unauthorized copying or distribution of this file, via any medium is strictly prohibited.
 */
package com.example.demo.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/***
 * @author Aditya Patil
 * @date 18-08-2025
 */

@Entity
@Table(name = "city")
@Builder
@NoArgsConstructor       // 👈 Needed for JPA
@AllArgsConstructor      // 👈 Needed for Builder
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String state;

    private String country;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
    private List<Temperature> temperatureList= new ArrayList<>();


    //helper functions
    public void addTemperature(Temperature t){
        this.temperatureList.add(t);
        t.setCity(this);
    }

    public void removeTemperature(Temperature t){
        this.temperatureList.remove(t);
        t.setCity(null);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Temperature> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(List<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }
}
