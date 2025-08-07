package com.spring.xmlPractice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/xmlPractice/practice.xml");
		
//		Vehicle obj = (Vehicle) context.getBean("bike");
//		obj.drive();
		
//		Tyre t = (Tyre) context.getBean("tyre");
//		System.out.println(t);
		
		Car obj = (Car) context.getBean("car");
		obj.drive();
	}
}
