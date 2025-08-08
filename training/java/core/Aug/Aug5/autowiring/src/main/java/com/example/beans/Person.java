package com.example.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

	private String name = "Lucy";

	/* @Autowired */ // using class fields
	private Vehicle vehicle;

	@Autowired // using constructor
	public Person(Vehicle vehicle) {
		System.out.println("Person bean created by Spring");
		this.vehicle = vehicle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	/* @Autowired */ // using setter auto wiring
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
