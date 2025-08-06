package com.spring_practice.creating_basic_bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring_practice.creating_basic_bean.beans.Animal;
import com.spring_practice.creating_basic_bean.beans.Vehicle;
import com.spring_practice.creating_basic_bean.config.ProjectConfig;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

		Vehicle vehicle = context.getBean(Vehicle.class);
		System.out.println(vehicle.getName());

		Vehicle vehicle1 = context.getBean("firstVehicle", Vehicle.class);
		System.out.println(vehicle1.getName());

		Vehicle vehicle2 = context.getBean("secondVehicle", Vehicle.class);
		System.out.println(vehicle2.getName());

		Vehicle vehicle3 = context.getBean("thirdVehicle", Vehicle.class);
		System.out.println(vehicle3.getName());

		Animal animal = context.getBean(Animal.class);
		System.out.println(animal.getName());
		animal.printHello();

		context.close();
	}
}
