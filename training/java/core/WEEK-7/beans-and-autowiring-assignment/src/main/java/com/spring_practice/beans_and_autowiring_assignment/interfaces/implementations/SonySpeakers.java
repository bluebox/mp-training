package com.spring_practice.beans_and_autowiring_assignment.interfaces.implementations;

import org.springframework.stereotype.Component;

import com.spring_practice.beans_and_autowiring_assignment.interfaces.Speakers;

@Component("SonySpeakers")
public class SonySpeakers implements Speakers {

	@Override
	public String makeSound() {
		return "making sound with Sony Speakers";
	}

}
