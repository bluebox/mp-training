package com.example.main;

import com.example.beans.Person;
import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import com.example.interfaces.Speakers;
import com.example.interfaces.Tyres;
import com.example.services.VehicleServices;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        System.out.println("Before retrieving the Person bean from the Spring Context");
        Person person = context.getBean(Person.class);
        System.out.println("After retrieving the Person bean from the Spring Context");
        Speakers s = context.getBean(Speakers.class);
        Tyres t = context.getBean(Tyres.class);
        Vehicle v = context.getBean(Vehicle.class);
        VehicleServices vs = context.getBean(VehicleServices.class);
        System.out.println(" Person with Vehicle Details:");
        System.out.println("Person Name :"+person.getName());
        System.out.println("Vehicle Name :"+v.getName());
        System.out.print("Vehicle Service used by Person is :");
        vs.playMusic();
        vs.moveVehicle(); 
       
        context.close();
    }
}