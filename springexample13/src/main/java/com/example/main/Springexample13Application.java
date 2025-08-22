package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;

@SpringBootApplication
public class Springexample13Application {

	public static void main(String[] args) {
		SpringApplication.run(Springexample13Application.class, args);
		 var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
	        String[] persons = context.getBeanNamesForType(Person.class);
	        Person person = context.getBean(Person.class);
	        String[] names = context.getBeanNamesForType(Vehicle.class);
//	        person.getVehicle().getVehicleServices().playMusic();
//	        person.getVehicle().getVehicleServices().moveVehicle();
	        Vehicle vehicle = context.getBean(Vehicle.class);
	        vehicle.getVehicleServices().playMusic();
	        vehicle.getVehicleServices().moveVehicle();
	}

}
