package com.spring.Practice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

//@Component
public class Vehicle {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@PostConstruct
//	public void initialize() {
//		System.out.println("inside post construct method");
//		this.name = "Honda";
//	}
//
//	@PreDestroy
//	public void destroy() {
//		System.out.println("inside pre destroy method");
//		System.out.println("Destroying Vehicle Bean");
//	}
}
