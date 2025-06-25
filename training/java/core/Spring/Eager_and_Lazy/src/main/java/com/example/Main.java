package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.dao.Member;
import com.example.dao.Person;
import com.example.dao.Vehicle;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Vehicle.class);
		Vehicle v=context.getBean(Vehicle.class);
		v.setName("Hero");
		System.out.println("Vehicle name is : "+v.getName());
		Person p=context.getBean(Person.class);
		p.setName("Ram");
		System.out.println("");
		Member m=context.getBean(Member.class);
		
	}
}
