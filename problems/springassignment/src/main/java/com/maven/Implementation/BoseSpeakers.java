package com.maven.Implementation;

import org.springframework.stereotype.Component;

import com.maven.Interface.Sounds;

@Component
public class BoseSpeakers implements Sounds {

	@Override
	public void makeSound() {
		System.out.println("Making sound with Bose speakers");
		
	}
	
}
