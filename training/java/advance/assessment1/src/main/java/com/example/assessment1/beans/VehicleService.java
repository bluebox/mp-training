package com.example.assessment1.beans;

public class VehicleService {
	
	private Speakers speakers;
	private Tyres tyres;
	public VehicleService(Speakers speakers, Tyres tyres) {
		this.speakers = speakers;
		this.tyres = tyres;
	}
	public void service()
	{
		speakers.makeSound();
		tyres.rotate();
	}
	

}
