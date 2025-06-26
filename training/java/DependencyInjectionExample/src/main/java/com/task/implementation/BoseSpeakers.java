package com.task.implementation;

import org.springframework.stereotype.Component;

import com.task.interfaces.Speakers;

@Component
public class BoseSpeakers implements Speakers{
	
	public String makeSound() {
		return "BoseSpeakers making sound";
	}
}
