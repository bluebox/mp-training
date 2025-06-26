package com.spring.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.spring.assignment.model.Speakers;
import com.spring.assignment.model.Tyres;

@Component
public class VehicleService {
	private Speakers speaker;
	private Tyres tyres;

	public Speakers getSpeaker() {
		return speaker;
	}

	public Tyres getTyres() {
		return tyres;
	}

	@Autowired
	public void setSpeaker(@Qualifier("speaker1") Speakers speaker) {
		this.speaker = speaker;
	}

	@Autowired
	public void setTyres(Tyres tyres) {
		this.tyres = tyres;
	}
	
	public void playMusic() {
		speaker.playMusic();
	}
	
	public void move() {
		tyres.move();
	}

}
