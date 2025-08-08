package com.example.beansmulti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.beans.Vehicle;

@Component
public class PersonMultiBean {

	private String name = "Lucy";
	private final Vehicle vehicle;

	@Autowired
	public PersonMultiBean(@Qualifier("vehicle2") Vehicle vehicle) {
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

}
