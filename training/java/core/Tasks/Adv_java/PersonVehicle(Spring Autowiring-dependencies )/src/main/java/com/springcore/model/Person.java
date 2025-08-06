package com.springcore.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component
@Primary
public class Person {
	private String personName;
	private Vehicle car;
	public Person() {
		super();
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Vehicle getCar() {
		return car;
	}
	
	@Autowired
	public void setCar(Vehicle car) {
		this.car = car;
	}
	
	public void hascar() {
		System.out.println("[ Person: "+personName+" has a "+car.getVehicleName()+"]");
		car.getVehicalServices();
	}
	
	@Override
	public String toString() {
		return "Person [personName=" + personName + ", car=" + car + "]";
	}
	
}
