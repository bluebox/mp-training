package com.spring.Practice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App {
	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		
		// Vehicle veh = context.getBean(Vehicle.class);
		
//		Vehicle veh = context.getBean("audiVehicle", Vehicle.class);
//		System.out.println("Vehicle name from Spring Context is: " + veh.getName());
//		veh = context.getBean("vehicle2", Vehicle.class);
//		System.out.println("Vehicle name from Spring Context is: " + veh.getName());
//		veh = context.getBean(Vehicle.class);
//		System.out.println("Vehicle name from Spring Context is: " + veh.getName());
		
		Vehicle vehicle = context.getBean(Vehicle.class);
		System.out.println("Component Vehicle name from Spring Context is: " + vehicle.getName());
		
		((AbstractApplicationContext) context).close();
	}

}
