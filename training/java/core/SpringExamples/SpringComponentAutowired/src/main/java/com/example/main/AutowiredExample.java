package com.example.main;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.beans.Customer;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredExample {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person person = context.getBean(Person.class);
        Vehicle vehicle = context.getBean(Vehicle.class);
//        System.out.println("person name(spring context): " + person.getName());
//        System.out.println("vehicle name(spring context): " + vehicle.getName());
        System.out.println("persons vehicle using Autowired: " + person.getVehicle());
        
//        Customer customer = context.getBean(Customer.class);
//        System.out.println(customer);
    }
}


