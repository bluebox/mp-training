package com.example15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Region {

    @Autowired
    private City city;

    public Region() {
        System.out.println("Region bean initialized");
    }

    public City getCityInstance() {
        return city;
    }
}