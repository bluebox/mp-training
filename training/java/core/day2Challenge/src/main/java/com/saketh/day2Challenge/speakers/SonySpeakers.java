package com.saketh.day2Challenge.speakers;

import org.springframework.stereotype.Component;

@Component
public class SonySpeakers implements Speakers {
	@Override
	public void makeSound() {
		System.out.println("Sony speakers making sound");
	}
}
