package com.example.assessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.assessment.beans.Person;

@Configuration
public class ProjectConfig {
	
	@Bean
	@Primary
	public Person person(){
		Person person=new Person();
		person.setName("Manoj");
		return person;
		
	}
	@Bean
	public Person person1() {
		Person person=new Person();
		person.setName("Chotu");
		return person;	
	}

}
