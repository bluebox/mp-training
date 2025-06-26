package com.spring.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.example1.Vehicle;

@Configuration
public class Config {
	
	@Bean
	public Vehicle xyz() {
		Vehicle vehicle = new Vehicle();
		return vehicle;
	}
}
