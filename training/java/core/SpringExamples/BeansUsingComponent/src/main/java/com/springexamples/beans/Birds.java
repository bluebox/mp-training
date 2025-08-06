package com.springexamples.beans;

import org.springframework.stereotype.Component;

@Component(value = "Bird")
public class Birds implements Animals {

	@Override
	public void makeSound() {
		System.out.println("Birds chirp chip chippp!");
	}

	@Override
	public void ability() {
		System.out.println("Birds fly in the air ");
	}

}
