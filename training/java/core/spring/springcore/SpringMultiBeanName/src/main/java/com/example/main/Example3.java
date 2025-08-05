package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example3 {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Vehicle veh1 = context.getBean("audiVehicle",Vehicle.class);
        System.out.println("vehicle name: " + veh1.getName());

        Vehicle veh2 = context.getBean("hondaVehicle",Vehicle.class);
        System.out.println("vehicle name: " + veh2.getName());

        Vehicle veh3 = context.getBean("ferrariVehicle",Vehicle.class);
        System.out.println("vehicle name: " + veh3.getName());
    }
}
