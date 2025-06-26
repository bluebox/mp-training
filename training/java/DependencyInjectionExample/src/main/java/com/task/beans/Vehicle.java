package com.task.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.task.services.VehicleServices;


@Lazy
@Component(value="vehicleBean")
public class Vehicle {
	private String name = "Honda";
	private final VehicleServices vehicleServices;
	
	@Autowired
	public Vehicle(VehicleServices vehicleServices) {
		this.vehicleServices = vehicleServices;
	}
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleServices getVehicleServices() {
        return vehicleServices;
    }

    public void printHello(){
        System.out.println(
            "Printing Hello from Component Vehicle Bean");
    }

    @Override
    public String toString(){
        return "Vehicle name is - "+name;
    }

}
