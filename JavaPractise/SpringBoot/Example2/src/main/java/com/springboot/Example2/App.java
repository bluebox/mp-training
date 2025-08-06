package com.springboot.Example2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springboot.Example2.beans.Person;
import com.springboot.Example2.config.ProjectConfig;

public class App 
{
    public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person person = context.getBean(Person.class);
        person.getVehicle().getVehicleServices().playMusic();
        person.getVehicle().getVehicleServices().moveVehicle();
    }
}
