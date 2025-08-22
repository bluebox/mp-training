package com.example.SpringExample6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;

@SpringBootApplication
public class SpringExample6Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringExample6Application.class, args);
		
		var context=new AnnotationConfigApplicationContext(ProjectConfig.class);
		Vehicle vehicle=context.getBean(Vehicle.class);
		System.out.println("Component Vehicle name from"  +
				             " Spring Context is:"  + vehicle.getName());
		vehicle.printHello();
		context.close();
	}

}
