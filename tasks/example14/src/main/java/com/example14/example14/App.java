package com.example14.example14;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.beans.Person;
import com.dummy.Vehicle;
import com.config.ProjectConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        
//        Person person1 = context.getBean(Person.class);
//        person1.setVehName("honda");
//        Person person2 = context.getBean(Person.class);
//        person2.printVehiclename();
//        person2.setVehName("mira");
//        person1.printVehiclename();
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            System.out.println("Thread was interrupted");
//        }
//        person1.printVehiclename();
        Vehicle veh1 = context.getBean(Vehicle.class);
        veh1.getName();
    }
}
