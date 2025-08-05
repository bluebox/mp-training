package dev.kaushik.autowiring.impl;

import org.springframework.stereotype.Component;

import dev.kaushik.autowiring.interfaces.Speakers;

@Component
public class SonySpeakers implements Speakers {

	@Override
	public void makeSound() {
		System.out.println("sony speakers making sound");
	}

}
