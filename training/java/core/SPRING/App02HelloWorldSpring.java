package com.maven.Rohan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {
	public static void main(String[] args) {
		
		
		 var context =new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
		 
		System.out.println(context.getBean("name"));	
		System.out.println(context.getBean("age"));
		System.out.println(context.getBean("person"));
		
		System.out.println(context.getBean("person2")); 
		//System.out.println(context.getBean(Person.class));
		System.out.println(context.getBean(Address.class));
		
		
	}
	
}