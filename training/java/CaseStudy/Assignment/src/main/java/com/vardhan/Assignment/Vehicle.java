package com.vardhan.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {

	@Autowired
	Service service;
	
	public void Automotive() {
		service.Roll();
		service.Sound();
	}
}
