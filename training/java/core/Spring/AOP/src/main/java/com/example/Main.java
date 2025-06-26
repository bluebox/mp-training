package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.ProductConfig;
import com.example.dao.Person;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
		Person p=context.getBean(Person.class);
		System.out.println(p.getName());
		p.setName("Ram");
		System.out.println(p.getName());
		p.setName("");
		System.out.println(p.toString());
	}
}
