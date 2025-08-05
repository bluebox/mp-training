package com.saketh.day2;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;


@Component
public class Service {
	Service(){
		System.out.println("This is Constructor");
		
	}
	@PostConstruct
	public void init() {
		System.out.println("This is iniit method");
	}
	public void intermediate() {
		System.out.println("This is intermediate method");
	}
	@PreDestroy
	public void end() {
		System.out.println("This is end method");
	}
}
