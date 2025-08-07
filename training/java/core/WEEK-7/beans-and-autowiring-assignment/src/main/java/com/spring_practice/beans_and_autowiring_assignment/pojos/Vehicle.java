package com.spring_practice.beans_and_autowiring_assignment.pojos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring_practice.beans_and_autowiring_assignment.services.VehicleServices;

@Component
public class Vehicle {
	
	private final VehicleServices vehicleServices;
	
	@Autowired
	public Vehicle(VehicleServices vehicleServices) {
		this.vehicleServices=vehicleServices;
	}
	
	public VehicleServices getVehicleServices() {
		return this.vehicleServices;
	}
	
	
}
