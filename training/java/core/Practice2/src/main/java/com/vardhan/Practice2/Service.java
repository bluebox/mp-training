package com.vardhan.Practice2;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Service {

	public Service() {
		System.out.println("Henlooo i am a Constructor");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("i'm the begining");
	}
	
	public void intermediate() {
		System.out.println("i'm the intermediate");
	}
	@PreDestroy
	public void end() {
		System.out.println("i'm the end");
	}
	

}
