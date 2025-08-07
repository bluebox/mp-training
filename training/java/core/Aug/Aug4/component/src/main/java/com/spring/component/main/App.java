package com.spring.component.main;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.component.beans.Vehicle;
import com.spring.component.config.ProjectConfig;

public class App {
	public static void main(String[] args) {

		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Vehicle vehicle = context.getBean(Vehicle.class);
		System.out.println("Component Vehicle name from Spring Context is: " + vehicle.getName());
		vehicle.printHello();
		context.close();

		var context1 = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Vehicle volkswagen = new Vehicle();
		volkswagen.setName("Volkswagen");
		Supplier<Vehicle> volkswagenSupplier = () -> volkswagen;

		Supplier<Vehicle> audiSupplier = () -> {
			Vehicle audi = new Vehicle();
			audi.setName("Audi");
			return audi;
		};

		Random random = new Random();
		int randomNumber = random.nextInt(10);
		System.out.println("randomNumber = " + randomNumber);

		if ((randomNumber % 2) == 0) {
			context1.registerBean("volkswagen", Vehicle.class, volkswagenSupplier);
		} else {
			context1.registerBean("audi", Vehicle.class, audiSupplier);
		}

		Vehicle volksVehicle = null;
		Vehicle audiVehicle = null;

		try {
			volksVehicle = context1.getBean("volkswagen", Vehicle.class);
		} catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
			System.out.println("Error while creating Volkswagen vehicle");
		}
		try {
			audiVehicle = context1.getBean("audi", Vehicle.class);
		} catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
			System.out.println("Error while creating Audi vehicle");
		}

		if (null != volksVehicle) {
			System.out.println("Programming Vehicle name from Spring Context is: " + volksVehicle.getName());
		} else {
			System.out.println("Programming Vehicle name from Spring Context is: " + audiVehicle.getName());
		}
	}
}
