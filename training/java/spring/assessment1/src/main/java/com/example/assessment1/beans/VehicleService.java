package com.example.assessment1.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleService {
	
	
	private Speakers speakers;
	
	private Tyres tyres;
	@Autowired
	public VehicleService(Speakers speakers, Tyres tyres) {
		this.speakers = speakers;
		this.tyres = tyres;
	}
	public void service()
	{
		speakers.makeSound();
		tyres.rotate();
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
	

}
