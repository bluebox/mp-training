package com.wiring.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
	@Bean
	public Bike bike() {
		Bike b = new Bike();
		b.setName("Palser NS 200");
		return b;
	}
	
	@Bean
	public Person person(Bike b) {
		Person p = new Person();
		p.setName("Rajesh Kumar");
		p.setBike(b);
		return p;
	}
	
}
