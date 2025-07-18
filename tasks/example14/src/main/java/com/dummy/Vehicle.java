package com.dummy;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
private String name;
	
	public Vehicle() {
		this.name="sri";
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
