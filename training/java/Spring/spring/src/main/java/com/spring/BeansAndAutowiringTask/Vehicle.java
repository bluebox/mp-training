package com.spring.BeansAndAutowiringTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {
	
	@Autowired
	VehicleService service;
}
