package com.karthik.example.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.karthik.example.beans.Vehicle;
import com.karthik.example.config.Config;

public class Main {

    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.setName("Honda City");
        System.out.println("Vehicle name from non-spring context is: " + vehicle.getName());
        
        var context = new AnnotationConfigApplicationContext(Config.class);
        
//        Vehicle veh = context.getBean(Vehicle.class);
//        System.out.println("Vehicle name from Spring Context is: " + veh.getName());

        Vehicle veh = context.getBean("vehicle",Vehicle.class);
        System.out.println("Vehicle name from Spring Context is: " + veh.getName());

        Vehicle veh1 = context.getBean("vehicle1",Vehicle.class);
        System.out.println("Vehicles name from Spring Context is: " + veh1.getName());

        Vehicle veh2 = context.getBean("vehicle2",Vehicle.class);
        System.out.println("Vehicles name from Spring Context is: " + veh2.getName());

        Vehicle veh3 = context.getBean("Safa",Vehicle.class);
        System.out.println("Vehicles name from Spring Context is: " + veh3.getName());
     
       
        String hello = context.getBean(String.class);
        System.out.println("String value from Spring Context is: " + hello);
        Integer num = context.getBean(Integer.class);
        System.out.println("Integer value from Spring Context is: " + num);
        
        System.out.println("String value from Spring Context is: " + context.getBean("World",String.class));
        System.out.println("String value from Spring Context is: " + context.getBean("HWorld",String.class));


    }
}
