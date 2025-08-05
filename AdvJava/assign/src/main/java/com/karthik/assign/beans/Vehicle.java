package com.karthik.assign.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karthik.assign.services.VehicleServices;

@Component(value="vehicleBean")
public class Vehicle {

	private String name="Honda";
	private final VehicleServices services;
	
	 @Autowired
	 public Vehicle(VehicleServices vehicleServices){
	        this.services = vehicleServices;
	 }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VehicleServices getServices() {
		return services;
	}

	@Override
	public String toString() {
		return "Vehicle name=" + name ;
	}

}
