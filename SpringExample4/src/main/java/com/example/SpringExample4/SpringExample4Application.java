package com.example.SpringExample4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;

@SpringBootApplication
public class SpringExample4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringExample4Application.class, args);
		
		var context=new AnnotationConfigApplicationContext(ProjectConfig.class);
		Vehicle veh=context.getBean(Vehicle.class);
		System.out.println("the primary vehicle from spring context is"+veh.getName());
	}

}
