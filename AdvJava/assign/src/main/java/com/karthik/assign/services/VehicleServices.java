package com.karthik.assign.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karthik.assign.interfaces.Speakers;
import com.karthik.assign.interfaces.Tyres;

@Component(value="vehicleServicesBean")
public class VehicleServices {
	
	@Autowired
	private Speakers speakers;
	private Tyres tyres;
	
	
	public void playMusic() {
		String Music = speakers.makeSound();
		System.out.println(Music);
	}
	
	public void moveVehicle() {
		String Move = tyres.rotate();
		System.out.println(Move);
	}

	public Speakers getSpeakers() {
		return speakers;
	}

	public void setSpeakers(Speakers speakers) {
		this.speakers = speakers;
	}

	public Tyres getTyres() {
		return tyres;
	}

	@Autowired
	public void setTyres(Tyres tyres) {
		this.tyres = tyres;
	}
	
	
	
	
	

}
