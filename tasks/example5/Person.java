package com.dom.Springbasic.example5;
import com.dom.Springbasic.example5.Vehicle;

public class Person {
	private String name;
	private Vehicle vehicle;
	public Person() {
		System.out.println("the person bean created by spring");
	}
	public String getName() {
		return name;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
