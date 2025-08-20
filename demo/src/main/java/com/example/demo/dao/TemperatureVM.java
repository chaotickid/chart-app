/*
 * Copyright (c) 2025 Mavenir Systems, Inc. All rights reserved.
 * This source code is the property of Mavenir Systems, Inc.
 * Unauthorized copying or distribution of this file, via any medium is strictly prohibited.
 */
package com.example.demo.dao;

/***
 * @author Aditya Patil
 * @date 18-08-2025
 */
public class TemperatureVM {
    private String date;
    private String min;
    private String max;
    private String avg;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }
}
