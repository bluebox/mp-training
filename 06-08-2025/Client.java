package com.example16;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

	public static void main(String[] args) {
		ApplicationContext ap = new ClassPathXmlApplicationContext("spri.xml");
		
		HelloWorld world1 = (HelloWorld) ap.getBean("hw");

		world1.setName("Dream");
		System.out.println("Hello object (hello1)" + " Your name is: " + world1.getName());

		HelloWorld world2 = (HelloWorld) ap.getBean("hw");

		System.out.println("Hello object (hello2)" + " Your name is: " + world2.getName());

		System.out.println("'World1' and 'World2'" + " are referring " + "to the same object: " + (world1 == world2));

		System.out.println("Address of object Geeks1: " + world1);
		System.out.println("Address of object Geeks2: " + world2);
		
	}
}