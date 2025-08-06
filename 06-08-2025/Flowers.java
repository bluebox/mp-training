package com.example7;

import org.springframework.stereotype.Component;

@Component
public class Flowers {

	private String smell;

	public String getSmell() {
		return smell;
	}

	public void setSmell(String smell) {
		this.smell = smell;
	}

	public void printHello() {
		System.out.println("Printing Hello from Component Flowers Bean");
	}
}