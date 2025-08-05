package com.karthik.assign.main;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.karthik.assign.beans.Person;
import com.karthik.assign.beans.Vehicle;
import com.karthik.assign.config.Config;

public class Main {

	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(Config.class);
		System.out.println(context);

		
		String[] persons = context.getBeanNamesForType(Person.class);
		System.out.println(Arrays.toString(persons));
		
		Person person = context.getBean(Person.class);
		String[] names = context.getBeanNamesForType(Vehicle.class);
		System.out.println(Arrays.toString(names));
		
		Vehicle vehicle = context.getBean(Vehicle.class);
		
		System.out.println(person);
		System.out.println(vehicle);

//		vehicle.getServices().playMusic();
//		vehicle.getServices().moveVehicle();

		person.getVehicle().getServices().playMusic();
		person.getVehicle().getServices().moveVehicle();
	}

}
