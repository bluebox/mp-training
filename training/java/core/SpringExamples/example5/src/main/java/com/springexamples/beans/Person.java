package com.springexamples.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

	private String name;
	private int age;
	private final Vehicle vehicle; 
	
	@Autowired
	public Person(Vehicle vehicle) {
		this.vehicle=vehicle;
		this.age=23;
		this.name="Raju";
	}
	
	public void display() {
		System.out.println("Done using Constructor injection name : " + name + "age : " + age+" vehicle name : "+vehicle.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
	
	
}
