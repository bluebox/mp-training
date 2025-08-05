package com.saketh.day2Challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.saketh.day2Challenge.speakers.Speakers;
import com.saketh.day2Challenge.tyres.Tyres;

@Component
public class Service {
	@Autowired
	Tyres tyres;
	@Autowired
	@Qualifier("sonySpeakers")
	Speakers speakers;
	public void playMusic() {
		speakers.makeSound();
	}
	public void move() {
		tyres.rotate();
	}
}
