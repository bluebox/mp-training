package com.karthik.assign.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="personBean")
public class Person {
	
	private String name= "Karthik";
	private final Vehicle vehicle;
	
	@Autowired
	public Person(Vehicle vechile) {
		this.vehicle=vechile;
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

	@Override
	public String toString() {
		return "Person name=" + name ;
	}
	
	

}
