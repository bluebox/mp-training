package com.example.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;

@SpringBootApplication
public class Springexample11Application {

	public static void main(String[] args) {
		SpringApplication.run(Springexample11Application.class, args);
		
		  var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
	        Person person = context.getBean(Person.class);
	        Vehicle vehicle = context.getBean(Vehicle.class);
	        System.out.println("Person name from Spring Context is: " + person.getName());
	        System.out.println("Vehicle name from Spring Context is: " + vehicle.getName());
	        System.out.println("Vehicle that Person own is: " + person.getVehicle());

	}

}
