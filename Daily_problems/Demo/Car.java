package com.Springpractise.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car{
	@Autowired
	@Qualifier("vehicle007")
	private Vehicle vehicle;
	public void drive() {
		System.out.println("its worKing priya...");
		
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	

}


