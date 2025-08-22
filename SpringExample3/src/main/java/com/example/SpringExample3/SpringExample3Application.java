package com.example.SpringExample3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;

@SpringBootApplication
public class SpringExample3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringExample3Application.class, args);
		
		var context=new AnnotationConfigApplicationContext(ProjectConfig.class);
		 Vehicle veh1 = context.getBean("bmwvehicle",Vehicle.class);
	        System.out.println("Vehicle name from Spring Context is: " + veh1.getName());
	        
	        Vehicle veh2= context.getBean("ferarivehicle",Vehicle.class);
	        System.out.println("Vehicle name from Spring Context is: " + veh2.getName());
	}

}
