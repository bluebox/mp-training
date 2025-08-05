package dev.tulasidhar.beans;

import org.springframework.stereotype.Component;

@Component
public class BoseSpeaker implements Speaker {

	@Override
	public void makeSound() {
		System.out.println("Bose Speakers are making sound with good base");

	}

}
