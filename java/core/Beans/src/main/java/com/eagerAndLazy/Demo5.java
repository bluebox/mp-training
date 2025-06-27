package com.eagerAndLazy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo5 {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person per = context.getBean(Person.class);
		per.setName("Jai");
		Vehicle veh=context.getBean(Vehicle.class);
		System.out.println("Person name from person bean : "+per.getName());
		System.out.println("Vehicle name from vehicle bean : "+veh.getName());
		System.out.println(per.getName()+" has own the "+per.getVehicle()+" vehicle!");
	}
}
