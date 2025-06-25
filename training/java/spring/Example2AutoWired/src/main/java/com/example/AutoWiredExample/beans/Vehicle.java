package com.example.AutoWiredExample.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.AutoWiredExample.components.Person;

@Component
public class Vehicle {
	private String name;
	/*
	 * throws unsatifiedDependency Exception
	@Autowired
	private Person person;
	*/
	@PostConstruct
	private void start()
	{
		System.out.println("Hey giving name ahhh");
		name="Royal Enfield";
	}

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	
}
