package com.spring.example2.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.example2.Vehicle;
import com.spring.example2.config.Config;

public class main {
	
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(Config.class);
		Vehicle vehicle = context.getBean(Vehicle.class);
		vehicle.getTyre().move();
		vehicle.getMusic().play();
		

	}
	
	

}
