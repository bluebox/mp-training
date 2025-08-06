package com.springexamples.beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.springexamples.interfaces.Speakers;

@Component
@Primary
public class SonySpeakers implements Speakers{

	@Override
	public void makeSound() {
		System.out.println("Music from Sony Speakers");
	}

}
