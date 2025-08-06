package com.springexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.springexamples.beans.Bike;

@Configuration
public class ProjectConfig {

	@Bean(value="bike1")
	//@Primary
	Bike bike1() {
		Bike bike=new Bike();
		bike.setName("pulsar ns");
		bike.setCc(200);
		return bike;
	}
	
	@Bean(name="bike2")
	Bike bike2() {
		Bike bike=new Bike();
		bike.setName("Hunter");
		bike.setCc(350);
		return bike;
	}
	
	@Bean("bike3")
	Bike bike3() {
		Bike bike=new Bike();
		bike.setName("Dominar");
		bike.setCc(400);
		return bike;
	}
}
