package dev.kaushik.autowiring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype") 
public class Person {

	@Autowired
	public Person(Vehicle vehicle) {
		System.out.println("Person bean created by Spring");
		this.vehicle = vehicle;
	}

	private String name;
	private Vehicle vehicle;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
