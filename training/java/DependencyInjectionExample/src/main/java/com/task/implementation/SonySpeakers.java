package com.task.implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.task.interfaces.Speakers;

@Component
@Primary
public class SonySpeakers implements Speakers{
	public String makeSound() {
		return "sony speakers making sound";
	}
}
