package com.spring.demo.springBasics;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.demo.beanExample.Vehicle;
import com.spring.demo.config.ProjectConfig;

public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Vehicle vehicle = new Vehicle();
		vehicle.setName("Honda City");
		System.out.println("Vehicle name from non-spring context is: " + vehicle.getName());

		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

		Vehicle veh = context.getBean(Vehicle.class);
		System.out.println("Vehicle name from Spring Context is: " + veh.getName());

		String hello = context.getBean(String.class);
		System.out.println("String value from Spring Context is: " + hello);
		Integer num = context.getBean(Integer.class);
		System.out.println("Integer value from Spring Context is: " + num);

		Vehicle veh2 = context.getBean(Vehicle.class);
		System.out.println("Vehicle name from Spring Context is: " + veh2.getName());

		Vehicle veh3 = context.getBean("vehicle1", Vehicle.class);
		System.out.println("Vehicle name from Spring Context is: " + veh3.getName());

		Vehicle veh4 = context.getBean("audiVehicle", Vehicle.class);
		System.out.println("Vehicle name from Spring Context is: " + veh4.getName());

		Vehicle veh5 = context.getBean("hondaVehicle", Vehicle.class);
		System.out.println("Vehicle name from Spring Context is: " + veh5.getName());

		Vehicle veh6 = context.getBean("ferrariVehicle", Vehicle.class);
		System.out.println("Vehicle name from Spring Context is: " + veh6.getName());
	}
}
