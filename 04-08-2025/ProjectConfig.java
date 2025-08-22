package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "mymaven")
public class ProjectConfig {

	@Bean
	Vehicle vehicle() {
		Vehicle v = new Vehicle();
		v.setName("Audi");
		return v;
	}

	@Bean
	String hello() {
		return "Hello World";
	}

	@Bean
	Integer number() {
		return 42;
	}

}
