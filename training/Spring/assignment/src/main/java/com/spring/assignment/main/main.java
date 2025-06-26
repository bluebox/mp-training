package com.spring.assignment.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.assignment.Person;
import com.spring.assignment.config.Config;

public class main {

	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(Config.class);
		Person person = context.getBean(Person.class);
		
		person.getVehicle().getVehicleService().getSpeaker().playMusic()
		;
		person.getVehicle().getVehicleService().getTyres().move();
		
		}

}
