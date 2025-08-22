
package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.beans.Person;
import com.example.beans.Vehicle;

@Configuration
public class ProjectConfig {

	@Bean
	public Vehicle vehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setName("hondaaa");
		return vehicle;
	}

	@Bean
	public Person person() {
		Person person = new Person();
		person.setName("charan");
		person.setVehicle(vehicle());
		return person;
	}

}
