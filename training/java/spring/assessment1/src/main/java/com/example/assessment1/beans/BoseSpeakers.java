package com.example.assessment1.beans;

import org.springframework.stereotype.Component;

@Component
public class BoseSpeakers implements Speakers{
	
	private String name="Bose";
	
	public String getName() {
		return name;
	}

	@Override
	public void makeSound() {
		System.out.println(" Bose Speakers Playing Music.....");
		
	}

}
