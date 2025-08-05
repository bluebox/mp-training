package dev.tulasidhar.learn_spring;

import org.springframework.stereotype.Component;

@Component
public class Turtle {
	String type;
	
	public Turtle(String type) {
		this.type = type;
	}
	
	public void describe() {
		System.out.println("This is a "+ type +" turtle!");
	}
}
