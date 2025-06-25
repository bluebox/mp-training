package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.ProductConfig;
import com.example.dao.Person;
import com.example.dao.Vehicle;

public class Main {
	public static void main(String[] args) {
		var context=new AnnotationConfigApplicationContext(ProductConfig.class);
		Person p = context.getBean(Person.class);
		System.out.println("Before initiallizing in the main class "+p.getName());
		p.setName("Ram");
		System.out.println("After initiallizing in the main class "+p.getName());
		Vehicle v=context.getBean(Vehicle.class);
		System.out.println("Before initiallizing in the main class "+v.getName());
		v.setName("Hero");
		System.out.println("After initiallizing in the main class "+v.getName());
		Person p1 = context.getBean(Person.class);
		System.out.println("Before initiallizing in the main class "+p.getName());
		p1.setName("Ramesh");
		System.out.println("After initiallizing in the main class "+p.getName());
		context.close();
	}
}
