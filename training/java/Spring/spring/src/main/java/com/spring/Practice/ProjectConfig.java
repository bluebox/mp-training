package com.spring.Practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.spring.Practice")
public class ProjectConfig {

////	@Bean
//	@Bean(name = "audiVehicle")
//	Vehicle vehicle1() {
//		Vehicle veh = new Vehicle();
//		veh.setName("Audi");
//		return veh;
//	}
//
//	@Primary
//	@Bean
//	Vehicle vehicle2() {
//		Vehicle veh = new Vehicle();
//		veh.setName("Honda");
//		return veh;
//	}

}
