package dev.kaushik.autowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.kaushik.autowiring.beans.Person;
import dev.kaushik.autowiring.config.ProjectConfig;

public class Main {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person person = context.getBean(Person.class);
        System.out.println("Vehicle that Person own is: " + person.getVehicle());
        person.getVehicle().getVehicleServices().getSpeakers().makeSound();
        person.getVehicle().getVehicleServices().getTyres().rotate();
	}
} 
