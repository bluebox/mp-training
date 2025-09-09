package com.vardhan.Practice3;

import org.springframework.stereotype.Component;

@Component
public class Marks {
	
	public Marks() {
		System.out.println("the bean created");
	}
	
	private String name = "Maths";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public void print() {
		System.out.println("hello from the marks");
	}

	@Override
	public String toString() {
		return "Subject name is :" + name;
	}

	
}
