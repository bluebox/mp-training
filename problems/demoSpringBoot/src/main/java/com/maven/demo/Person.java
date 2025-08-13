package com.maven.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("PersonBean")
public class Person {
    
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	private String name;

    private Vehicle vehicle;
	@Autowired
	public Person(@Qualifier("Vehicle") Vehicle vehicle) {
		this.name="Prabhas";
        this.vehicle=vehicle;
	}
	public String getName() {
		return name;
	}
//	  public Person(String name) {
//		this.name = "prabhas";
//	}
	  public void setName(String name) {
		  this.name = name;
	  }
	  
	  
	  @javax.annotation.PostConstruct
	  public void init() {
		  System.out.println("Person name has been ]]]]]]");

		  this.name="Prabhas";
		  System.out.println("Person name has been changed");
	  }
	  

		

}
