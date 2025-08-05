package dev.kaushik.autowiring.impl;

import org.springframework.stereotype.Component;

import dev.kaushik.autowiring.interfaces.Speakers;

@Component
public class BoseSpeakers implements Speakers {

	@Override
	public void makeSound() {
		System.out.println("bose speakers making sound");
	}

}
