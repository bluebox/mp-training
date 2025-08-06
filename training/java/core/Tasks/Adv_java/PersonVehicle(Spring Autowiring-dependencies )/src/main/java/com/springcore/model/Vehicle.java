package com.springcore.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Vehicle {
	private String vehicleName;
	private VehicalServices vehicalServices;
	@Autowired
	public Vehicle(VehicalServices vehicalServices) {
		this.vehicalServices = vehicalServices;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	public void getVehicalServices() {
		vehicalServices.playMusic();
		vehicalServices.moveVehicle();
	}
	
	@Override
	public String toString() {
		return "Vehicle [vehicleName=" + vehicleName + ", vehicalServices=" + vehicalServices + "]";
	}
}
