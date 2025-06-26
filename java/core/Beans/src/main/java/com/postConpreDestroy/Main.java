package com.postConpreDestroy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var context = new AnnotationConfigApplicationContext(Person.class);
		Person p = context.getBean(Person.class);
		p.hello();
		System.out.println("Person name is : "+p.getName());
		context.close();
	}

}
