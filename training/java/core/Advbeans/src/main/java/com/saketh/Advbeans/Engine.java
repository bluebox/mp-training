package com.saketh.Advbeans;

import org.springframework.stereotype.Component;

@Component
public class Engine {
	String name;
	public void start() {
		System.out.println("dukdukdukdukduk");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
