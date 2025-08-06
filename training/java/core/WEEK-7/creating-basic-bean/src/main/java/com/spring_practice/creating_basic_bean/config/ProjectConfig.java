package com.spring_practice.creating_basic_bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.spring_practice.creating_basic_bean.beans.Vehicle;

@Configuration
@ComponentScan(basePackages = "com.spring_practice.creating_basic_bean.beans")
public class ProjectConfig {

	@Bean(name = "firstVehicle")
	Vehicle vehicle1() {
		Vehicle vehicle = new Vehicle();
		vehicle.setName("bike");
		return vehicle;
	}

	@Bean(value = "secondVehicle")
	Vehicle vehicle2() {
		Vehicle vehicle = new Vehicle();
		vehicle.setName("car");
		return vehicle;
	}

	@Primary
	@Bean("thirdVehicle")
	Vehicle vehicle3() {
		Vehicle vehicle = new Vehicle();
		vehicle.setName("helicopter");
		return vehicle;
	}

}
