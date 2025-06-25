package com.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.person.Person;

public class AutowiredDemo {
	@Autowired
	static Person per=new Person();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var context = new AnnotationConfigApplicationContext(Vehicle.class);
		
		Vehicle veh = context.getBean(Vehicle.class);
		veh.setName("Mahendra xuv 700");
				
		var context1 = new AnnotationConfigApplicationContext(Person.class);
		
		Person per = context1.getBean(Person.class);
		per.setId(200);
		per.setName("Rajesh Kumar");
		
		System.out.println("Person ID is : "+per.getId());

		System.out.println(per.getName()+" have "+veh.getName()+" vehicle.");
		
	}
}
