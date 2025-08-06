package com.springboot.Example2.beans;

import org.springframework.stereotype.Component;

@Component
public class Person {
	private String name="Lucy";
	
	private final Vehicle vehicle;
	public Person(Vehicle vehicle) {
		this.vehicle= vehicle;
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
}
