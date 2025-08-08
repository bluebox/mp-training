package com.spring.beanScope;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UserController {

	@Autowired
	User user;
	
	public UserController() {
		System.out.println("User Controller init");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("User Controller HashCode : " + this.hashCode() + " User Object HashCode : " + user.hashCode());
	}
}
