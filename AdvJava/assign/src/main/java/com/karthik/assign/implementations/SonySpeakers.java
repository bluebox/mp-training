package com.karthik.assign.implementations;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.karthik.assign.interfaces.Speakers;

@Component
@Primary
public class SonySpeakers implements Speakers{

	@Override
	public String makeSound() {
		return "Sony Speakers plays music";
	}
	

}
