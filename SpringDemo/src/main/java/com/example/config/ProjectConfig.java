package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.beans.Vehicle;

@Configuration
public class ProjectConfig {

	@Bean
	Vehicle vehicle() {
		Vehicle veh = new Vehicle();
		veh.setName("BMW");
		return veh;
	}
	@Bean
	String hello() {
	
		return "hello world";
	}
	@Bean
	Integer number() {
		return 20;
	}
}
