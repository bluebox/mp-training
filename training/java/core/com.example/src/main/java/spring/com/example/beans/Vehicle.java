package spring.com.example.beans;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.com.example.services.VehicleServices;


@Component
public class Vehicle {
	private String name="hondaCB350";
	private VehicleServices vehicleService;
	
	//@Autowired
	Vehicle(VehicleServices vehicleService){
		this.vehicleService=vehicleService;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public VehicleServices getVehicleservice() {
		return vehicleService;
	}
//	public void setVehicleservice(VehicleServices vehicleservice) {
//		this.vehicleService = vehicleservice;
//	}
	@Override
	public String toString() {
		return "vehicle is  "+this.name;
	}
		
}
