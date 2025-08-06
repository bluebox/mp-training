package com.springexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springexamples.beans.Chocolate;
import com.springexamples.beans.Student;

@Configuration
public class ProjectConfig {
	
	@Bean
	Student student() {
		Student s1=new Student();
		s1.setId(007);
		s1.setName("James Bond");
		return s1;
	}
	
	@Bean
	Chocolate candy() {
		Chocolate chocolate=new Chocolate();
		chocolate.setName("kitkat");
		chocolate.setPrice(20);
		chocolate.setFlavour("waffer");
		return chocolate;
	}
}
