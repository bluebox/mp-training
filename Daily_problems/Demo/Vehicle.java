package com.Springpractise.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("vehicle007")
public class Vehicle {
	private String v_name;
	
	public void run() {
		System.out.println("vechile is running ..");
	}

	public String getV_name() {
		return v_name;
	}

	public void setV_name(String v_name) {
		this.v_name = v_name;
	}
	
	

}
class Motor extends Vehicle{
	public void work() {
		System.out.println("motor is started");
	}
	
}
