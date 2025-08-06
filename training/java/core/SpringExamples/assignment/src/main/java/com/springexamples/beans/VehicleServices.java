package com.springexamples.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springexamples.interfaces.Speakers;
import com.springexamples.interfaces.Tyres;

@Component
public class VehicleServices {
	
	private final Speakers speakers;
	private final Tyres tyres;
	
	@Autowired
	public VehicleServices(Speakers speakers, Tyres tyres) {
		
		this.speakers=speakers;
		this.tyres=tyres;
	}

	public Speakers getSpeakers() {
		return speakers;
	}

	public Tyres getTyres() {
		return tyres;
	}
	
//	private Speakers speaker;
//	private Tyres tyres;
//	
//	public Speakers getSpeaker() {
//		return speaker;
//	}
//	
//	@Autowired
//	public void setSpeaker(Speakers speaker) {
//		this.speaker = speaker;
//	}
//
//	public Tyres getTyres() {
//		return tyres;
//	}
//	
//	@Autowired
//	@Qualifier(value="michelin")
//	public void setTyres(Tyres tyres) {
//		this.tyres = tyres;
//	}

	
//	public void Music() {
//		speaker.makeSound();
//	}
//	
//	public void move() {
//		tyres.rotate();
//	}
}
