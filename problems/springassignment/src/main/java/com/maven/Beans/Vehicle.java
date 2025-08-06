package com.maven.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maven.Services.VehicleService;

@Component("VehicleBean")
public class Vehicle {
     private String name="Tata";
     private VehicleService vehicleservice;
     
     @Autowired
	 public Vehicle(VehicleService vehicleservice) {
		this.vehicleservice = vehicleservice;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public VehicleService getVehicleservice() {
		 return vehicleservice;
	 }

	 public void setVehicleservice(VehicleService vehicleservice) {
		 this.vehicleservice = vehicleservice;
	 }

	 @Override
	 public String toString() {
		return "Vehicle [name=" + name + ", vehicleservice=" + vehicleservice + "]";
	 }
	 
	 
     
}
