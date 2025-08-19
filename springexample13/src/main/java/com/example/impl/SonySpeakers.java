package com.example.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.interfaces.Speakers;

@Component
@Primary
public class SonySpeakers implements Speakers {

	@Override
	public String makeSound() {
		return "playing music with sony speakers";
	}

}
