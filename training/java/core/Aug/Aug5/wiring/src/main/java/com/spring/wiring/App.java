package com.spring.wiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.beans.Person;
import com.spring.beans.Vehicle;
import com.spring.config.ProjectConfig;
import com.spring.config.ProjectConfigParameter;

public class App {
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person person = context.getBean(Person.class);
		Vehicle vehicle = context.getBean(Vehicle.class);
		System.out.println("Person name from Spring Context is: " + person.getName());
		System.out.println("Vehicle name from Spring Context is: " + vehicle.getName());
		System.out.println("Vehicle that Person own is: " + person.getVehicle());

		var context1 = new AnnotationConfigApplicationContext(ProjectConfigParameter.class);
		Person person1 = context.getBean(Person.class);
		Vehicle vehicle1 = context.getBean(Vehicle.class);
		System.out.println("Person name from Spring Context is: " + person1.getName());
		System.out.println("Vehicle name from Spring Context is: " + vehicle1.getName());
		System.out.println("Vehicle that Person own is: " + person1.getVehicle());
	}
}
