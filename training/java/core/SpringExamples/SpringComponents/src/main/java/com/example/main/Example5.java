package com.example.main;

import com.example.beans.Fruit;
import com.example.beans.Laptop;
import com.example.beans.Library;
import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example5 {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
//        Vehicle vehicle = context.getBean(Vehicle.class);
//        Fruit f=context.getBean(Fruit.class);
//        f.setName("orange");
//        f.setPrice(20);
//        System.out.println(f);
//        System.out.println("vehicle component from spring context: " + vehicle.getName());
//        vehicle.printHello();
//        
//        Laptop l=context.getBean(Laptop.class);
//        System.out.println(l);
        
//        ---------------------------------------------------

//        Vehicle volkswagen = new Vehicle();
//        volkswagen.setName("Volkswagen");
//        Supplier<Vehicle> volkswagenSupplier = () -> volkswagen;
//
//
//        Supplier<Vehicle> audiSupplier = () -> {
//            Vehicle audi = new Vehicle();
//            audi.setName("Audi");
//            return audi;
//        };
//
//        Random random = new Random();
//        int randomNumber = random.nextInt(10);
//        System.out.println("randomNumber = " + randomNumber);
//
//        if((randomNumber% 2) == 0){
//            context.registerBean("volkswagen",
//                    Vehicle.class,volkswagenSupplier);
//        }else{
//            context.registerBean("audi",
//                    Vehicle.class,audiSupplier);
//        }
//        Vehicle volksVehicle = null;
//        Vehicle audiVehicle = null;
//        try {
//            volksVehicle = context.getBean("volkswagen",Vehicle.class);
//        }catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
//            System.out.println("Error while creating Volkswagen vehicle");
//        }
//        try {
//            audiVehicle = context.getBean("audi",Vehicle.class);
//        }catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
//            System.out.println("Error while creating Audi vehicle");
//        }
//
//        if(null != volksVehicle){
//            System.out.println("Programming Vehicle name from Spring Context is: " + volksVehicle.getName());
//        }else{
//            System.out.println("Programming Vehicle name from Spring Context is: " + audiVehicle.getName());
//        }

        
//        --------------------------------------------------------

        Library library = context.getBean(Library.class);
        System.out.println("Retrieved Library: " + library);

        context.close();

        
    }
}




