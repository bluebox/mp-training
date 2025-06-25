package com.component;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import beans.com.bean.Person;
import beans.com.config.ProjectConfig;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var context = new AnnotationConfigApplicationContext(Person.class);
		
		Person per = context.getBean(Person.class);
		per.setId(100);
		per.setName("Ganesh");
		System.out.println("Person ID (spring context) is : "+per.getId());

		System.out.println("Person name (spring context) is : "+per.getName());
		
	}

}
