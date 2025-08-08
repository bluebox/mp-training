package com.spring.beanScope;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class User {

	public User() {
		System.out.println("User Initialized");
	}

	@PostConstruct
	public void init() {
		System.out.println("User Object HashCode : " + this.hashCode());
	}
}
