package com.noUniqueNameException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.noUniqueNameException.Person;

@Configuration
public class ProjectConfig {
	
	@Bean
	Person person1() {
		var per = new Person();
		per.setId(20);
		per.setName("Jai");
		return per;
	}
	
	@Bean
	Person person2() {
		var per = new Person();
		per.setId(21);
		per.setName("Uday");
		return per;
	}
	
	
	
}
