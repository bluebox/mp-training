package com.example.AutoWiredExample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.AutoWiredExample.beans.Vehicle;
import com.example.AutoWiredExample.components.Person;
import com.example.AutoWiredExample.config.ProjectConfig;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ProjectConfig.class);  
        Person person=context.getBean(Person.class);
        person.setName("Manoj");
        System.out.println(person);
        
        Vehicle vehicle=context.getBean(Vehicle.class);
        vehicle.setName("Jupyter");
        System.out.println(vehicle.getName());
        
        
    
    }
}
