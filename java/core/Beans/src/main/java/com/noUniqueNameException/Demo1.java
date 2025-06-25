package com.noUniqueNameException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo1 {

public static void main(String[] args) {
		
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		
		Person per1 = context.getBean("person1",Person.class);
		System.out.println("Person 1 ID (spring context) is : "+per1.getId());

		System.out.println("Person 1 name (spring context) is : "+per1.getName());
		
		Person per2 = context.getBean("person2",Person.class);
		System.out.println("Person 2 ID (spring context) is : "+per2.getId());

		System.out.println("Person 2 name (spring context) is : "+per2.getName());
		
	}

}