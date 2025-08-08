package com.spring.implementation;

import org.springframework.stereotype.Component;

import com.spring.interfaces.Speakers;

@Component
public class BoseSpeakers implements Speakers {

	public String makeSound() {
		return "Playing music with Bose speakers";
	}

}
