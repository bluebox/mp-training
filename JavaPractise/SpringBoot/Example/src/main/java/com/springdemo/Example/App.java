package com.springdemo.Example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springdemo.Example.beans.Vehicle;
import com.springdemo.Example.config.ProjectConfig;

public class App 
{
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Vehicle vehicle = context.getBean(Vehicle.class);
		System.out.println("Component Vehicle name from Spring Context is: " + vehicle.getName());
		vehicle.printHello();
		context.close();
	}
}
