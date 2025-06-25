package com.example.assessment1.beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SonySpeakers implements Speakers{
	
	private String name="Sony";


	@Override
	public void makeSound() {
		System.out.println("playing Varshinchey.......");
		
	}
	public String getName() {
		return name;
	}

}
