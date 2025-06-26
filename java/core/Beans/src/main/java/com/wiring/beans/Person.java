package com.wiring.beans;

public class Person {
	Person(){
		System.out.println("Person bean initiated");
	}
	private String name;
	private Bike bike;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Bike getBike() {
		return bike;
	}
	
	public void setBike(Bike bike) {
		this.bike = bike;
	}
}
