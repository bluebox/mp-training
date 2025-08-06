package com.springexamples.beans;

import org.springframework.stereotype.Component;

@Component
public class Food {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
