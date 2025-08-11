package com.spring.customAOP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleService {

	@Autowired
	private Speakers speakers;
	private Tyres tyres;

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

	@LogAspect
	public String playMusic(boolean vehicleStarted, Song song) {
		System.out.println("speakers injected? " + (speakers != null));
		return speakers.makeSound(song);
	}

	public String moveVehicle(boolean vehicleStarted) {
		System.out.println("Tyres injected? " + (tyres != null));
		return tyres.rotate();
	}

	public String applyBrake(boolean vehicleStarted) {
		return tyres.stop();
	}
}
