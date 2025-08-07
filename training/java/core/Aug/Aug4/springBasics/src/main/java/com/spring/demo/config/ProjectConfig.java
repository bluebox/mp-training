package com.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.spring.demo.beanExample.Vehicle;

@Configuration
public class ProjectConfig {

	@Bean
	@Primary
	Vehicle vehicle() {
		var veh = new Vehicle();
		veh.setName("Audi 8");
		return veh;
	}

	@Bean
	Vehicle vehicle1() {
		var veh = new Vehicle();
		veh.setName("Audi");
		return veh;
	}

	@Bean
	Vehicle vehicle2() {
		var veh = new Vehicle();
		veh.setName("Honda");
		return veh;
	}

	@Bean
	Vehicle vehicle3() {
		var veh = new Vehicle();
		veh.setName("Ferrari");
		return veh;
	}

	@Bean
	String hello() {
		return "Hello World";
	}

	@Bean
	Integer number() {
		return 16;
	}

	@Bean(name = "audiVehicle")
	Vehicle vehicle4() {
		var veh = new Vehicle();
		veh.setName("Audi");
		return veh;
	}

	@Bean(value = "hondaVehicle")
	Vehicle vehicle5() {
		var veh = new Vehicle();
		veh.setName("Honda");
		return veh;
	}

	@Bean("ferrariVehicle")
	Vehicle vehicle6() {
		var veh = new Vehicle();
		veh.setName("Ferrari");
		return veh;
	}
}
