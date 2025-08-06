package com.maven.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Primary
@Component("Vehicle")
@Scope("prototype")
public class Vehicle {

	private String name;
	
	public Vehicle() {
		this.name ="vehicle name";
	}

	@Override
	public String toString() {
		return "Vehicle [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

        
}
