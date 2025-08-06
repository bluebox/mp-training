package com.example.inversio_control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class InversioControlApplication {

	public static void main(String[] args) {
		//Spring container
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MobilesConfig.class);
		Mobiles obj=context.getBean("getOnePlusObject",Mobiles.class);
		obj.getModelAndColor();
		obj.getWeight();
		
		Mobiles obj1=context.getBean("getRedmiObject",Mobiles.class);
		obj1.getModelAndColor();
		obj1.getWeight();
		
		Mobiles obj2=context.getBean("getRealmeObject",Mobiles.class);
		obj2.getModelAndColor();
		obj2.getWeight();
		
		
		var context1= new AnnotationConfigApplicationContext(VehicleConfig.class);
		
		Vehicle v1=context1.getBean("vehicle1",Vehicle.class);
		System.out.println("vehicle name is"+v1.getName());
		
		Vehicle v2=context1.getBean("vehicle2",Vehicle.class);
		System.out.println("Vehicle is "+v2.getName());
		
		Vehicle v3=context1.getBean("vehicle3",Vehicle.class);
		System.out.println("vehicle is "+v3.getName());
		
	}

}
