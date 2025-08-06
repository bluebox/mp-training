package com.spring_practice.creating_basic_bean.beans;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Animal {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void printHello() {
		System.out.println("Prinring Hello from Animal obj...");
	}

	@PostConstruct
	public void initialize() {
		this.name = "Initialized Name";
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Animal object is going to destroy...");
	}

}
