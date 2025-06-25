package com.example.AOP.beans;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
//@EnableAspectJAutoProxy
public class Vehicle {
	private String name;
	
	public void playMusic(boolean vehicleStarted)
	{
		System.out.println("Playing music");
	}
	public void move(boolean vehicleStarted)
	{
		System.out.println("Vehicle is moving");

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
