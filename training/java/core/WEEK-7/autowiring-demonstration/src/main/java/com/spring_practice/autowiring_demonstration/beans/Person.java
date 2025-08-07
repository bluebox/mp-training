package com.spring_practice.autowiring_demonstration.beans;

import org.springframework.stereotype.Component;


@Component
public class Person {
	private String nameString;
	
//	@Autowired
	private Vehicle vehicle;

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

//	@Autowired
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
//	@Autowired
//	public Person(Vehicle vehicle) {
//	super();
//	System.out.println("Person created");
//	this.vehicle = vehicle;
//}
	
//	@Autowired
	public Person(Vehicle vehicle) {
		super();
		System.out.println("Person Created");
		this.vehicle = vehicle;
	}
	
//	public Person() {
//		System.out.println("Person created default");
//	}

	
	
	
}
