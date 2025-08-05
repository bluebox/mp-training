package com.beanSample;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@Configuration
@ComponentScan(basePackages="com.beanSample")
public class Configurate {
	
	@Bean
	Vehicle veh() {
		
		Vehicle veh=new Vehicle();
		veh.setType("car");
		return veh;
	}
	
	@Bean
	String hello() {
		return "hello, World!!!!";
	}

	@Bean
	Integer value() {
		return 16;
	}
}
