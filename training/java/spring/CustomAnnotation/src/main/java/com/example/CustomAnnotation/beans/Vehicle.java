package com.example.CustomAnnotation.beans;

import org.springframework.stereotype.Component;

import com.example.CustomAnnotation.annotations.LogAspect;

@Component
public class Vehicle {
	private String name;
	
	@LogAspect
	public String playMusic(boolean vehicleStarted) {
		System.out.println("Playing music");
		return "returingahhhh";
	}

	public String move(boolean vehicleStarted) {
		System.out.println("Vehicle is moving");
		return "returingahhhh";

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
