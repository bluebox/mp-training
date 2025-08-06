package com.springexamples.beans;

import org.springframework.stereotype.Component;

import com.springexamples.interfaces.Speakers;

@Component
public class BoseSpeakers implements Speakers {

	@Override
	public void makeSound() {
		System.out.println("Music from Bose Speakers ");
	}

}
