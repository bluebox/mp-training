package com.springexamples.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Showroom {
	
	private Vehicle vehicle;
	private String ShowroomName="Branded Vehicles";
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	@Autowired
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public String getShowroomName() {
		return ShowroomName;
	}
	public void setShowroomName(String showroomName) {
		ShowroomName = showroomName;
	}
	
	public void info() {
		System.out.println("Done using Setter injection show room name : "+ShowroomName+" car name : "+vehicle.getName());
	}
	
}
