package com.eagerAndLazy;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
	
	private String name = "Mahendra Thar";
	
	public Vehicle() {
		System.out.println("Vehicle bean initiated..");
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

}
