package com.example.SpringExample7;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;

@SpringBootApplication
public class SpringExample7Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringExample7Application.class, args);
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Vehicle volkswagen = new Vehicle();
		volkswagen.setName("Volkswagen");
		Supplier<Vehicle> volkswagenSupplier = () -> volkswagen;
//        System.out.println(volkswagenSupplier);

		Supplier<Vehicle> audiSupplier = () -> {
			Vehicle audi = new Vehicle();
			audi.setName("Audi");
			return audi;

		};
		Random random = new Random();
		int randomNumber = random.nextInt(10);
		System.out.println("randomNumber = " + randomNumber);

		if ((randomNumber % 2) == 0) {
			context.registerBean("volkswagen", Vehicle.class, volkswagenSupplier);
		} else {
			context.registerBean("audi", Vehicle.class, audiSupplier);
		}
		Vehicle volksVehicle = null;
		Vehicle audiVehicle = null;
		try {
			volksVehicle = context.getBean("volkswagen", Vehicle.class);
		} catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
			System.out.println("Error while creating Volkswagen vehicle");
		}
		try {
			audiVehicle = context.getBean("audi", Vehicle.class);
		} catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
			System.out.println("Error while creating Audi vehicle");

		}
		if (volksVehicle != null) {
			System.out.println("Programming Vehicle name from Spring Context is: " + volksVehicle.getName());
		} else {
			System.out.println("Programming Vehicle name from Spring Context is: " + audiVehicle.getName());
		}

	}

}
