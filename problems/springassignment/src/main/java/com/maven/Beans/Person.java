package com.maven.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("PersonBean")
public class Person {
    private String name="Prabhas";
     private final Vehicle vehicle;
     
     @Autowired     
	 public Person(Vehicle vehicle) {
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
