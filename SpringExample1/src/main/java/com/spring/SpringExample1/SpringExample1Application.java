package com.spring.SpringExample1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.beans.Vehicle;
import com.spring.config.ProjectConfig;

@SpringBootApplication
public class SpringExample1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringExample1Application.class, args);
		
		 var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		 //Vehicle veh = context.getBean(Vehicle.class); ambiguity
		  Vehicle veh = context.getBean("vehicle1",Vehicle.class);
		 	System.out.println("vehicle name from spring context is " + veh.getName());
		
	}

}
