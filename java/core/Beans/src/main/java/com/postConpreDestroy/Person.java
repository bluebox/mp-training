package com.postConpreDestroy;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class Person {
	private String name;
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	@PostConstruct
	public void initialize() {
		this.name = "Jai";
		System.out.println("Person initialized!");
	}
	@PreDestroy
	public void destroy() {
		System.out.println("Person bean is destroyed!");
	}
	
	
	public void hello() {
		System.out.println("Hello, man!");
	}

}
