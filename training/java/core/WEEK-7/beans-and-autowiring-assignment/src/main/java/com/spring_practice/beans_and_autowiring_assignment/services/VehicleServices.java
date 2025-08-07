package com.spring_practice.beans_and_autowiring_assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring_practice.beans_and_autowiring_assignment.interfaces.Speakers;
import com.spring_practice.beans_and_autowiring_assignment.interfaces.Tyres;

@Service
public class VehicleServices {
	private Speakers speakers;
	private Tyres tyres;
	
	@Autowired
	public VehicleServices(@Qualifier("boseSpeakers")Speakers speakers, Tyres tyres) {
		this.speakers = speakers;
		this.tyres=tyres;
	}
	
	public String makeSound() {
		return speakers.makeSound();
	}
	
	public String moveVehicle() {
		return tyres.moveVehicle();
	}
}
