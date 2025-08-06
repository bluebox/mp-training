package com.springexamples.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Garage {
	
	@Autowired
	private Vehicle vehicle;
	
	public void info() {
		System.out.println("Done using Field injection Car name :"+vehicle.getName());
	}
}
