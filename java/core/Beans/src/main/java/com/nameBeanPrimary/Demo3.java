package com.nameBeanPrimary;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Demo3 {

	public static void main(String[] args) {
		
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		
		Vehicle vehicle = context.getBean(Vehicle.class);
		System.out.println("Primary Vehicle Bean model is : "+vehicle.getName());
		
		Vehicle v1 = context.getBean("audiVehicle",Vehicle.class);
		System.out.println("Audi Vehicle model is : "+v1.getName());
		
		Vehicle v2 = context.getBean("tataVehicle",Vehicle.class);
		System.out.println("Tata Vehicle model is : "+v2.getName());
		
		Vehicle v3 = context.getBean("mahendraVehicle",Vehicle.class);
		System.out.println("Mahendra Vehicle model is : "+v3.getName());
	}
}
