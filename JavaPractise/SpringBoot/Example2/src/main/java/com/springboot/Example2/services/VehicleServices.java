package com.springboot.Example2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.springboot.Example2.interfaces.Speakers;
import com.springboot.Example2.interfaces.Tyres;

@Component
public class VehicleServices {
	private final Speakers speakers;
	private final Tyres tyres;

	@Autowired
	public VehicleServices( Speakers speakers, @Qualifier("michelinTyres") Tyres tyres) {
		this.speakers = speakers;
		this.tyres = tyres;
	}

	public void playMusic() {
		String music = speakers.makeNoise();
		System.out.println(music);
	}

	public void moveVehicle() {
		String status = tyres.rotate();
		System.out.println(status);
	}
}
