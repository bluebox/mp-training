package com.example.springExample2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringExample2Application {

	public static void main(String[] args) {
		var context=new AnnotationConfigApplicationContext(VehicleConfig.class);
//		Vehicle v1=context.getBean("audiVehicle",Vehicle.class);
//		System.out.println("Vehicle name is "+v1.getName());
//		
//		Vehicle v2=context.getBean("tharVehicle",Vehicle.class);
//		System.out.println("Vehicle name is "+v2.getName());
		
//		Vehicle v3=context.getBean(Vehicle.class);
//		System.out.println("Vehicle name is "+v3.getName());
		
		Vehicle vehicle=context.getBean(Vehicle.class);
		System.out.println(vehicle.getName());
		vehicle.printHello();
		
	}

}
