package com.example.assignment.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Person {
	private String name;
	private String id;
	
	private final Vehicle vehicle;
	@Autowired
	public Person(Vehicle vehicle) {
		this.vehicle=vehicle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	

}
