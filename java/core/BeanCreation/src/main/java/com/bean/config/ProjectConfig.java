package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beans.Person;

@Configuration
public class ProjectConfig {
	
	@Bean
	Person person() {
		var per = new Person();
		per.setName("Jai");
		return per;
	}
	
	@Bean
	String hello() {
		return "Spring Demo called!";
	}
}
