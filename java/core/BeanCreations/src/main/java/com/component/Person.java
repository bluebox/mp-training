package com.component;

import org.springframework.stereotype.Component;

@Component
public class Person {
	private int id;
	private String name;
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public String hello() {
		return "using xml method bean called!";
	}
}
