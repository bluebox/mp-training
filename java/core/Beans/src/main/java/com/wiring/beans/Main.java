package com.wiring.beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person p = context.getBean(Person.class);
		System.out.println("Person name from person bean is : "+p.getName());
		Bike b = context.getBean(Bike.class);
		System.out.println("Bike name from bike bean is : "+b.getName());
		System.out.println(p.getName()+" has own "+p.getBike().getName());
	}

}
