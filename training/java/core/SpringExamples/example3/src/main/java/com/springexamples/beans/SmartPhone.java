package com.springexamples.beans;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class SmartPhone {
	
	private String name;
	private double price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@PostConstruct
	public void setfields() {
		this.name="Apple";
		this.price=330.5;
	}
	
	@PreDestroy
	public void end() {
		System.out.println("Called before closing of the bean");
	}
	
	public void info() {
		System.out.println("Name : "+name+" \nprice : "+price);
	}
	
}
