package com.spring.implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.spring.interfaces.Speakers;

@Component
@Primary
public class SonySpeakers implements Speakers {

	public String makeSound() {
		return "Playing music with Sony speakers";
	}

}
