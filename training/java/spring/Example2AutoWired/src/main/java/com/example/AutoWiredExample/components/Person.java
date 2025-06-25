package com.example.AutoWiredExample.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.AutoWiredExample.beans.Vehicle;

@Component
public class Person {
	

	private String name;
	private String password;
	//Field autowired
	//@Autowired
	private final Vehicle vehicle;
	
	//constroctor AutoWired
	@Autowired
	public Person(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "Person [name=" + name + ", Vehicle=" + vehicle.getName() + "]";
	}
}
