package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.beans.Vehicle;

@Configuration
@ComponentScan(basePackages = "com.example.beansmulti")
public class ProjectConfigMultiBean {

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

}
