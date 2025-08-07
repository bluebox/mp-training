package com.spring_practice.beans_and_autowiring_assignment.pojos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
	private final Vehicle vehicle;

	@Autowired
	public Person(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
		
}
