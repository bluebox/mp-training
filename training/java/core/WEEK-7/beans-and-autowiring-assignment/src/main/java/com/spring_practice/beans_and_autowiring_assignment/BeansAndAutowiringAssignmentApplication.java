package com.spring_practice.beans_and_autowiring_assignment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring_practice.beans_and_autowiring_assignment.config.ProjectConfig;
import com.spring_practice.beans_and_autowiring_assignment.pojos.Person;
import com.spring_practice.beans_and_autowiring_assignment.pojos.Vehicle;

public class BeansAndAutowiringAssignmentApplication {

	public static void main(String[] args) {
		var context=new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person person=context.getBean(Person.class);
		Vehicle vehicle=person.getVehicle();
		
		System.out.println(vehicle.getVehicleServices().makeSound());
		System.out.println(vehicle.getVehicleServices().moveVehicle());
		
	}

}
