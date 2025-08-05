package com.saketh.day2Challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
	@Autowired
	Vehicle car;
	public void personVehicle() {
		car.vehicleService();
	}
}
