package com.vardhan.Assignment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App{
	
	public static void main(String[]args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configu.class);
		Person person = context.getBean(Person.class);
		person.Human();
		
		
	}
	
}