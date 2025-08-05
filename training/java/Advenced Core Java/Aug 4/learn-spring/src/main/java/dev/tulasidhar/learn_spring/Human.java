package dev.tulasidhar.learn_spring;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;



@Component
public class Human {
	
	@PostConstruct
	public void init() {
		System.out.println("I am a human and my object have been initialized");
	}
	
	public void speak() {
		System.out.println("Human says Hello!");
	}
}
