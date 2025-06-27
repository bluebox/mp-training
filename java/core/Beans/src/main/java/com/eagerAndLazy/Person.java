package com.eagerAndLazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Person {
	private String name;
	private Vehicle veh;
	
	@Autowired
	public Person(Vehicle veh) {
		this.veh=veh;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setVehicle(Vehicle veh) {
		this.veh=veh;
	}
	
	public String getVehicle() {
		return veh.getName();
	}
}
