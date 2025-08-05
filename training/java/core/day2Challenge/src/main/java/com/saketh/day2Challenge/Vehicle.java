package com.saketh.day2Challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {
	@Autowired
	Service service;
	public void vehicleService() {
		service.move();
		service.playMusic();
	}
}
