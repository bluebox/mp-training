package com.springcore.model;

import org.springframework.stereotype.Component;

@Component
public class BoseSpeakers implements Speakers{

	@Override
	public void makeSound() {
		System.out.println("Playing music with BoseSpeakers");
	}

}
