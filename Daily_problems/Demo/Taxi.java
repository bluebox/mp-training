package com.Springpractise.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) 
public class Taxi {
	private String company="uber";
	private final Car car;
	@Autowired
	public Taxi( Car car) {
		this.car=car;
		
	}
	public void road() {
		this.car.drive();
		System.out.println("taxi is running");
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}

