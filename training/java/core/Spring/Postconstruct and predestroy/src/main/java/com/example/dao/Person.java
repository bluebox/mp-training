package com.example.dao;

import org.springframework.stereotype.Component;

@Component
public class Person {
	String name;
	Vehicle vehicle;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}
