package com.spring_practice.autowiring_demonstration.beans;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
	private String nameString;

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public Vehicle(String nameString) {
		super();
		this.nameString = nameString;
	}
	
	public Vehicle() {
		System.out.println("Vehicle Created");
	}

}
