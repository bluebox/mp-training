package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.ProjectConfig;
import com.example.dao.Person;
import com.example.dao.Vehicle;

public class Main {
	public static void main(String[] args) {
		var c=new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person p=c.getBean(Person.class);
		System.out.println(p.toString());
		Vehicle v=c.getBean(Vehicle.class);
		System.out.println(v.toString());
		Vehicle v1=c.getBean("vehicle1",Vehicle.class);
		System.out.println(v1.toString());
		Person p1=c.getBean("person1",Person.class);
		System.out.println(p1.toString());
	}
}
