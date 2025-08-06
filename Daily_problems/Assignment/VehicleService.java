package com.example.assignment.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class VehicleService {

	private Speakers speakers;
	private Tyres tyres;
	public void playmusic() {
		speakers.makeSound();
		System.out.println("the music is playing ");
		
	}
	
	public void move() {
		tyres.rotate();
		System.out.println("the vehicle is moving ");
	}
	@Autowired
	public VehicleService(@Qualifier("bosespeakers") Speakers speakers,@Qualifier("bridgetyres") Tyres tyres) {
		this.speakers=speakers;
		this.tyres=tyres;
	}
	
}
