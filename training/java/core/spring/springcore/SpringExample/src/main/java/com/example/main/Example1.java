package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example1 {

    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.setName("Honda City");
        System.out.println("name from non spring context: " + vehicle.getName());
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Vehicle veh = context.getBean(Vehicle.class);
        System.out.println("vehicle name from spring context: " + veh.getName());

        String hello = context.getBean(String.class);
        System.out.println("String val from spring context: " + hello);
        Integer num = context.getBean(Integer.class);
        System.out.println("Integer val from spring context: " + num);

    }
}
