package com.spring.config;

import org.springframework.context.annotation.Bean;

import com.spring.beans.Person;
import com.spring.beans.Vehicle;

public class ProjectConfig {

	@Bean
	public Vehicle vehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setName("Toyota");
		return vehicle;
	}

	@Bean
	public Person person() {
		Person person = new Person();
		person.setName("Lucy");
		person.setVehicle(vehicle());
		return person;
	}

}
