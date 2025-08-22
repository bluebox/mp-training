package com.example.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
    	 SpringApplication.run(SpringDemoApplication.class, args); 
    		
    	Vehicle vehicle = new Vehicle();
        vehicle.setName("Honda City");
        System.out.println("Vehicle name from non-spring context is: " + vehicle.getName());
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Vehicle veh = context.getBean(Vehicle.class);
        System.out.println("Vehicle name from Spring Context is: " + veh.getName());
        
        
        
        String hello = context.getBean(String.class);
        System.out.println("String value from Spring Context is: " + hello);
        Integer num = context.getBean(Integer.class);
        System.out.println("Integer value from Spring Context is: " + num);

       
    }
}