package com.saketh.day2Challenge.speakers;

import org.springframework.stereotype.Component;

@Component
public class BoseSpeakers implements Speakers{
	@Override
	public void makeSound() {
		System.out.println("Bose speakers making sound");
	}
}
