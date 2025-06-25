package com.example.dao;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Vehicle [name=" + name + "]";
	}
	
}

