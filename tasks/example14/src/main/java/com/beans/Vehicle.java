package com.beans;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
	private String name;
	
	public Vehicle() {
		this.name="ram";
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
        return "Vehicle name is - "+name;
    }
}
