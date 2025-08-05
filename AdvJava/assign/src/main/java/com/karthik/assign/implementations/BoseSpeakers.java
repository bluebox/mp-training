package com.karthik.assign.implementations;

import org.springframework.stereotype.Component;

import com.karthik.assign.interfaces.Speakers;

@Component
public class BoseSpeakers implements Speakers{

	@Override
	public String makeSound() {
		return "Bose Speakers plays music";
	}
	

}
