package dev.tulasidhar.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {
	
	@Autowired
	public VehicleService vehicleService;
	
}
