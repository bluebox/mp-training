package com.example.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.config.BeanDefinition;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class Person {
	String name;
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}	
}
