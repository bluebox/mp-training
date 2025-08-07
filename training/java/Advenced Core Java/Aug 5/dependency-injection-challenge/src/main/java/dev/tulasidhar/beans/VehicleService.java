package dev.tulasidhar.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class VehicleService {
	
	@Autowired
	Tyres tyres;
	
	@Autowired(req=false)
	Speaker speaker;
		
	public void playMusic() {
		speaker.makeSound();
	}
	
	public void move() {
		tyres.rotate();
	}
}
