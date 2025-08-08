package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.beans.Person;
import com.spring.beans.Vehicle;

@Configuration
public class ProjectConfigParameter {
	@Bean
	public Vehicle vehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setName("Toyota");
		return vehicle;
	}

	@Bean
	public Person person(Vehicle vehicle) {
		Person person = new Person();
		person.setName("Lucy");
		person.setVehicle(vehicle);
		return person;
	}
}
