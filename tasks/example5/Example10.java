package com.dom.Springbasic.example5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example10 {
	public static void main(String[] args) {
		var context=new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person person=context.getBean(Person.class);
		Vehicle vehicle=context.getBean(Vehicle.class);
		System.out.println("Person name from Spring Context is: " + person.getName());
        System.out.println("Vehicle name from Spring Context is: " + vehicle.getName());
        System.out.println("Vehicle that Person own is: " + person.getVehicle());

	}

}
