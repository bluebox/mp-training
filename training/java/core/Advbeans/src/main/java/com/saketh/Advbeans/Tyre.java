package com.saketh.Advbeans;

import org.springframework.stereotype.Component;

@Component
public class Tyre {
	String name;
	public void rotate() {
		System.out.println("The tyre "+name+ " is ratating");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
