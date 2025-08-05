package dev.tulasidhar.beans;

import org.springframework.stereotype.Component;

@Component
public class BoatSpeaker implements Speaker {

	@Override
	public void makeSound() {
		System.out.println("Boat speaker is making sound and it is pretty balanced");

	}

}
