package com.maven.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.maven.demo.*;
import com.maven.demo.Person;
@Configuration
@ComponentScan(basePackages = "com.maven.demo")
public class appconfig{

	@Primary
	@Bean("name")
	public String StringBean() {
		return "DefaultClass";
	}
	
	@Primary
	@Bean("Animal")
	public Animal AnimalBean() {
		return new Animal("yellow","wild");
	}

	
	@Bean("Animal2")
	public Animal AnimalBean2() {
		return new Animal("white","domestic");
	}
	
	
	@Bean("name")
	public String StringBean1() {
		return "DefaultClass1";
	}
	
}