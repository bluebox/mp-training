package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.dao.Member;
import com.example.dao.Person;
import com.example.dao.Vehicle;

public class Main {
	public static void main(String[] args) {
		var context=new AnnotationConfigApplicationContext(Person.class);
		Person p1 = context.getBean(Person.class);
		Person p2 = context.getBean(Person.class);
		if(p1==p2) {
			System.out.println("Scope of Person class is singleton");
			System.out.println("Hashcode of p1 : "+p1.hashCode());
			System.out.println("Hashcode of p2 : "+p2.hashCode());
		}
		else {
			System.out.println("Scope of Person class is prototype");
		}
		var context1=new AnnotationConfigApplicationContext(Vehicle.class);
		Vehicle v1 = context1.getBean(Vehicle.class);
		Vehicle v2 = context1.getBean(Vehicle.class);
		if(v1==v2) {
			System.out.println("Scope of Person class is singleton");
			System.out.println("Hashcode of v1 : "+v1.hashCode());
			System.out.println("Hashcode of v2 : "+v2.hashCode());
			
		}
		else {
			System.out.println("Scope of Person class is prototype");
			System.out.println("Hashcode of v1 : "+v1.hashCode());
			System.out.println("Hashcode of v2 : "+v2.hashCode());
		}
		var context3=new AnnotationConfigApplicationContext(Member.class);
		Member m1 = context3.getBean(Member.class);
		Member m2 = context3.getBean(Member.class);
		if(m1==m2) {
			System.out.println("Scope of Person class is singleton");
			System.out.println("Hashcode of m1 : "+m1.hashCode());
			System.out.println("Hashcode of m2 : "+m2.hashCode());
		}
		else {
			System.out.println("Scope of Person class is prototype");
		}
	}
}
