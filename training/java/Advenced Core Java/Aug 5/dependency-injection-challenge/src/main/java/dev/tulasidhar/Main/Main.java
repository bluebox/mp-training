package dev.tulasidhar.Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.tulasidhar.beans.Person;

public class Main {
	public static void main(String[] args) {
		
		
		ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
		Person person = context.getBean(Person.class);
		
		
		person.vehicle.vehicleService.move();
		person.vehicle.vehicleService.playMusic();
	}
}
