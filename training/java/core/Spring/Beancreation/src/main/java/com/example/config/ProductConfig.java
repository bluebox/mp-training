package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.dao.Person;

@Configuration
@ComponentScan("com.example.dao")
public class ProductConfig {
	@Bean
	@Primary
	public Person person1() {
		Person p=new Person();
		p.setName("Bhanu Prakash");
		return p;
	}
}
