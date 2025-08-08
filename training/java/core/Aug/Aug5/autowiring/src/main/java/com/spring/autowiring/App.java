package com.spring.autowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.beansmulti.PersonMultiBean;
import com.example.config.ProjectConfig;
import com.example.config.ProjectConfigMultiBean;

public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person person = context.getBean(Person.class);
		Vehicle vehicle = context.getBean(Vehicle.class);
		System.out.println("Person name from Spring Context is: " + person.getName());
		System.out.println("Vehicle name from Spring Context is: " + vehicle.getName());
		System.out.println("Vehicle that Person own is: " + person.getVehicle());
		context.close();

		var context1 = new AnnotationConfigApplicationContext(ProjectConfigMultiBean.class);
		PersonMultiBean person1 = context1.getBean(PersonMultiBean.class);
		System.out.println("Person name from Spring Context is: " + person1.getName());
		System.out.println("Vehicle that Person own is: " + person1.getVehicle());

	}
}
