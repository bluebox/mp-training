package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.config.ProductConfig;
import com.example.dao.Person;

public class Main {
	public static void main(String[] args) {
		var context=new ClassPathXmlApplicationContext("web.xml");
		Person p=context.getBean(Person.class);
		System.out.println(p.getName());
		p.setName("Raghav");
		System.out.println(p.getName());
		AnnotationConfigApplicationContext c1 = new AnnotationConfigApplicationContext(Person.class);
		Person p1=c1.getBean(Person.class);
		System.out.println(p1.getName());
		p1.setName("Raghu");
		System.out.println(p1.getName());
		var c2=new AnnotationConfigApplicationContext(ProductConfig.class);
		Person p2=c2.getBean(Person.class);
		System.out.println(p2.getName());
		p2.setName("Raghu");
		System.out.println(p2.getName());
	}
}
