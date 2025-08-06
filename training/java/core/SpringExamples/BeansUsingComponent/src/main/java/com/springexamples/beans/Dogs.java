package com.springexamples.beans;

import org.springframework.stereotype.Component;

@Component(value = "Dog")
public class Dogs implements Animals {

	@Override
	public void makeSound() {
		System.out.println("Dog Barks bow bowww!");
	}

	@Override
	public void ability() {
		System.out.println("Dog walks on land");
	}

}
