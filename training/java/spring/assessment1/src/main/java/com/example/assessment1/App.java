package com.example.assessment1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.assessment1.beans.Person;
import com.example.assessment1.config.projectConfig;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(projectConfig.class);

        Person person=context.getBean(Person.class);
        System.out.println(person.getName());
        person.getVehicle().getVehicleService().service();
        context.close();
    }
}
