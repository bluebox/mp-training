package com.spring_practice.autowiring_demonstration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring_practice.autowiring_demonstration.beans.Person;
import com.spring_practice.autowiring_demonstration.beans.Vehicle;
import com.spring_practice.autowiring_demonstration.config.ProjectConfig;

public class AutowiringDemonstrationApplication {

	public static void main(String[] args) {
		
		var context=new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person person=context.getBean(Person.class);
		Vehicle vehicle=context.getBean(Vehicle.class);
	}

}
