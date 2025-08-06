package com.springcore.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class VehicalServices {
	private Speakers speakers;
	private Tyres tyres;

	@Autowired
	public VehicalServices(Speakers speakers,Tyres tyres) {
		this.speakers=speakers ;
		this.tyres = tyres;
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

	public void setTyres(Tyres tyres) {
		this.tyres = tyres;
	}
	
	public void playMusic() {
		speakers.makeSound();
	}
	
	public void moveVehicle() {
		tyres.rotate();
	}
	

}
