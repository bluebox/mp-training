package com.spring.beansAndAutowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.beans.Person;
import com.spring.beans.Vehicle;
import com.spring.config.ProjectConfig;

public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

		Person person = context.getBean(Person.class);

		Vehicle vehicle = context.getBean(Vehicle.class);

		vehicle.getVehicleServices().playMusic();
		vehicle.getVehicleServices().moveVehicle();

		person.getVehicle().getVehicleServices().playMusic();
		person.getVehicle().getVehicleServices().moveVehicle();
	}
}
