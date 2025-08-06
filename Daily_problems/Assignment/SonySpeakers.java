package com.example.assignment.Assignment;

import org.springframework.stereotype.Component;

@Component
public class SonySpeakers implements Speakers{

	@Override
	public void makeSound() {
		System.out.println("sony speakers are working exceptionally");
		
	}

}
