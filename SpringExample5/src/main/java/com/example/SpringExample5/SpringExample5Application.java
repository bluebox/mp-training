package com.example.SpringExample5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;

@SpringBootApplication
public class SpringExample5Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringExample5Application.class, args);
		var context=new AnnotationConfigApplicationContext(ProjectConfig.class);
		Vehicle veh=context.getBean(Vehicle.class);
		 System.out.println("Component Vehicle name from Spring Context is: " + veh.getName());
	        veh.printHello();

	}

}
