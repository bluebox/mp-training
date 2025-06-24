package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.dao.Person;
import com.example.dao.Vehicle;

@Configuration
public class ProjectConfig {
	@Bean
	Person person() {
		Person p=new Person();
		p.setName("Raghu");
		return p;
	}
	@Bean
	Vehicle vehicle1() {
		Vehicle v=new Vehicle();
		v.setName("Audi");
		return v;
	}
	@Bean
	Vehicle vehicle2() {
		Vehicle v=new Vehicle();
		v.setName("Benz");
		return v;
	}
	@Bean
	@Primary
	Vehicle vehicle3() {
		Vehicle v=new Vehicle();
		v.setName("Chevrolet");
		return v;
	}
	Person person1(Vehicle v) {
		Person p=new Person();
		p.setName("Lucy");
		p.setVehicle(v);
		return p;
	}
}
