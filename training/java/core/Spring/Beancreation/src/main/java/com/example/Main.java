package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.config.ProductConfig;
import com.example.dao.Member;
import com.example.dao.Person;
import com.example.dao.Vehicle;

public class Main {
	public static void main(String[] args) {
		var context=new ClassPathXmlApplicationContext("web.xml");
		Person p=context.getBean(Person.class);
		System.out.println("Before setting name using xml: "+p.getName());
		p.setName("Raghav");
		System.out.println("After  setting name using xml: "+p.getName());
		AnnotationConfigApplicationContext c1 = new AnnotationConfigApplicationContext(ProductConfig.class);
		try{
			Person p1=c1.getBean(Person.class);
			System.out.println(p1.getName());
			
		}
		catch (Exception e) {
			System.out.println("There is no person class connected to ProductConfig");
		}
		try{
			Member p1=c1.getBean(Member.class);
			System.out.println(p1.getName());
			
		}
		catch (Exception e) {
			System.out.println("There is no member class connected to ProductConfig");
		}
		try{
			Vehicle p1=c1.getBean(Vehicle.class);
			System.out.println(p1.getName());
			
		}
		catch (Exception e) {
			System.out.println("There is no vehicle class connected to ProductConfig");
		}
		var c2=new AnnotationConfigApplicationContext(ProductConfig.class);
		Person p2=c2.getBean(Person.class);
		System.out.println("Before setting name using Configuration: "+p2.getName());
		p2.setName("Raghu");
		System.out.println("After setting name using Configuration: "+p2.getName());
		AnnotationConfigApplicationContext c3 = new AnnotationConfigApplicationContext(Member.class);
		Member m = c3.getBean(Member.class);
		m.setName("Ram");
		System.out.println("Member name after calling through Member class which is not a bean: "+m.getName());
	}
}
