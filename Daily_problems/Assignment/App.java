package com.example.assignment.Assignment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	var context=new AnnotationConfigApplicationContext(VehicleConfig.class);
    	Person person=context.getBean(Person.class);
    	person.getVehicle().vservice().playmusic();
    	person.getVehicle().vservice().move();

    	

    }
}
