package com.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.person.Person;

@ComponentScan("com.person")
public class ComponentScanDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var context = new AnnotationConfigApplicationContext(Vehicle.class);
		
		Vehicle veh = context.getBean(Vehicle.class);
		veh.setName("TATA");
				
		var context1 = new AnnotationConfigApplicationContext(Person.class);
		
		Person per = context1.getBean(Person.class);
		per.setId(100);
		per.setName("Ganesh");
		
		System.out.println("Person ID is : "+per.getId());

		System.out.println(per.getName()+" have "+veh.getName()+" vehicle.");
		
	}
}
