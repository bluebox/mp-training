package com.spring.example1.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.example.config.Config;
import com.spring.example1.Vehicle;

public class main {

	public static void main(String[] args) {
		
		var context = new AnnotationConfigApplicationContext(Config.class);
		Vehicle veh1 = context.getBean(Vehicle.class);
		veh1.move();
		veh1.music();

	}

}
