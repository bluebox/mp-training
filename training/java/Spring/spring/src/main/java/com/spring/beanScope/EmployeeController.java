package com.spring.beanScope;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

	@Autowired
	Employee user;
	
	public EmployeeController() {
		System.out.println("Employee Controller init");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Employee Controller HashCode : " + this.hashCode() + " Employee Object HashCode : " + user.hashCode());
	}
}
