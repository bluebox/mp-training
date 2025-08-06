package com.springboot.Example2.implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.springboot.Example2.interfaces.Speakers;
@Component
@Primary
public class SonySpeakers implements Speakers{

	@Override
	public String makeNoise() {
		// TODO Auto-generated method stub
		return "Making noise with Sony Speakers .";
	}

}
