package com.example.assessment1.beans;

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
