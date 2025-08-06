package com.maven.Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.maven.Beans.Person;
import com.maven.Beans.Vehicle;
import com.maven.config.AppConfiguration;

public class App 
{
    public static void main( String[] args )
    {
        
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Person person = context.getBean("PersonBean",Person.class);
        person.getVehicle().getVehicleservice().playMusic();
        person.getVehicle().getVehicleservice(). Tyresinstalled();
    	
    	
    	
    	
    	
    }
}
