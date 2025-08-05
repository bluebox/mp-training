package com.saketh.day2Challenge.speakers;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SonySpeakers implements Speakers {
	public void makeSound() {
		System.out.println("Sony speakers making sound");
	}
}
