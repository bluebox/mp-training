package com.springdemo.Example.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;


@Component
public class Vehicle {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@PostConstruct
	public void initialize() {
		this.name="Honda";
	}
	@PreDestroy
	public void destroy() {
		System.out.println("Successfully destroyed .");
	}
	public void printHello() {
		System.out.println("Hi I am from vehicle class");
	}
}
