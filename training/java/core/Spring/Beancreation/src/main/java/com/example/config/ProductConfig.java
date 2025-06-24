package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.dao.Person;

@Configuration
@ComponentScan("com.example.dao")
public class ProductConfig {
	@Autowired
	Person p;
	public Person person() {
		p.setName("Bhanu");
		return p;
	}
}
