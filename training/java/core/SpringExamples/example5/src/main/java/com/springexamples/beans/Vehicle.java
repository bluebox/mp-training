package com.springexamples.beans;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
	
	private String Vehiclename="audi";

	public String getName() {
		return Vehiclename;
	}

	public void setName(String name) {
		this.Vehiclename = name;
	}
	
	
}
