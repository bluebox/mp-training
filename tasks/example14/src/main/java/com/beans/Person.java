package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Person {

	private String name;
	@Autowired
	private Vehicle vehicle;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void printVehiclename() {
		System.out.println(this.vehicle.getName());
	}
	
	@Async
	public void setVehName(String name) {
		    try {
		        Thread.sleep(3000); // delay for 3 seconds
		    } catch (InterruptedException e) {
		        Thread.currentThread().interrupt(); // good practice to restore interrupt status
		        System.out.println("Thread was interrupted");
		    }
		    this.vehicle.setName(name);		

	}
	
	public Person() {
	}
}
