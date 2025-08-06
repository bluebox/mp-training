package com.springexamples.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
	
	private final Vehicle vehicle;

	@Autowired
	public Person(Vehicle vehicle) {
		this.vehicle=vehicle;
	}
	
	public void sound() {
		vehicle.getVehicleServices().getSpeakers().makeSound();
	}
	
	public void move() {
		vehicle.getVehicleServices().getTyres().rotate();
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
}
