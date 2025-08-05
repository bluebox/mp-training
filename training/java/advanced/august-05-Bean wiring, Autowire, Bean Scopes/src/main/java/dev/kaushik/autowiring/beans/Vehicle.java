package dev.kaushik.autowiring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.kaushik.autowiring.services.VehicleServices;

@Component
public class Vehicle {

	@Autowired
	private VehicleServices vehicleServices;

//	@Autowired
//	public Vehicle(VehicleServices vehicleServices) {
//
//		System.out.println("Vehicle bean created by Spring");
//	}

	private String name = "Toyota";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void printHello() {
		System.out.println("Printing Hello from Component Vehicle Bean");
	}

	@Override
	public String toString() {
		return "Vehicle name is - " + name;
	}

	public VehicleServices getVehicleServices() {
		return vehicleServices;
	}
}
