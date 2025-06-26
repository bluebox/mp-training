package com.example.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Person {
	String name;

	public String getName() {
		System.out.println("In the get method :\nvalue = "+name);
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Bean
	public String toString() {
		if(name=="") {
			throw new NullPointerException();
		}
		return "Name : "+this.name;
	}
}
