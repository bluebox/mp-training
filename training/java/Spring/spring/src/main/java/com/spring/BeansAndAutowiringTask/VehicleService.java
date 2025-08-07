package com.spring.BeansAndAutowiringTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class VehicleService {

	@Autowired
	@Qualifier("bridgeStoneTyres")
	Tyres tyres;
	
	@Autowired
//	@Qualifier("boseSpeakers")
	Speakers speakers;
	
	public void rotate() {
		tyres.rotate();
	}
	
	public void makeSound() {
		speakers.makeSound();
	}
}
