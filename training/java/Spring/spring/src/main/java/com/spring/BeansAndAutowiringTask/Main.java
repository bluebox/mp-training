package com.spring.BeansAndAutowiringTask;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person person = context.getBean(Person.class);
		
		person.vehicle.service.makeSound();
		person.vehicle.service.rotate();
	}
}
