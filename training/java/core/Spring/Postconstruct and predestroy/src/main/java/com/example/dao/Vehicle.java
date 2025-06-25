package com.example.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Vehicle {
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@PostConstruct
	public void init() {
		this.name="Yamaha";
		System.out.println("It is constructed");
	}
	@Override
	public String toString() {
		return "Vehicle [name=" + name + "]";
	}
	@PreDestroy
	public void destroy() {
		System.out.println("It is destroyed");
	}
}
