package com.example.impl;

import org.springframework.stereotype.Component;

import com.example.interfaces.Speakers;


@Component
public class JblSpeakers implements Speakers {

	@Override
	public String makeSound() {
		return "playing music with jbl speakers";
	}
	
}
