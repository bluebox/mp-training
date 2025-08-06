package com.springboot.Example2.implementation;

import org.springframework.stereotype.Component;

import com.springboot.Example2.interfaces.Speakers;

@Component
public class BoseSpeakers implements Speakers{

	@Override
	public String makeNoise() {
		return "Making noise with Bose Speakers";
	}
	
}
