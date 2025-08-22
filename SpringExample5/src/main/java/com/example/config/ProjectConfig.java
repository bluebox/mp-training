package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.beans.Vehicle;

@Configuration
@ComponentScan(basePackages = "com.example.beans")
public class ProjectConfig {
	@Bean
	Vehicle vehicle() {
		Vehicle veh = new Vehicle();
		veh.setName("bike....");
		return veh;

	}
}