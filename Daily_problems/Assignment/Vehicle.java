package com.example.assignment.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Vehicle {
	
	private final VehicleService vehicleservice;
	@Autowired
	public Vehicle(VehicleService vehicleservice) {
		this.vehicleservice=vehicleservice;
	}
	public VehicleService vservice() {
		return this.vehicleservice;
	}

}
