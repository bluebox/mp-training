package com.dom.Springbasic.example6;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dom.Springbasic.example6.Person;
import com.dom.Springbasic.example6.Vehicle;


public class Example11 {
	public static void main(String[] args) {
	var context=new AnnotationConfigApplicationContext(ProjectConfig.class);
	Person person=context.getBean(Person.class);
	Vehicle vehicle=context.getBean(Vehicle.class);
	System.out.println("Person name from Spring Context is: " + person.getName());
    System.out.println("Vehicle name from Spring Context is: " + vehicle.getName());
    System.out.println("Vehicle that Person own is: " + person.getVehicle());

	
	}

}
