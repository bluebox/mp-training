package com.maven.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.maven.demo.*;
//import com.maven.demo.Person;
@ComponentScan(basePackages = "com.maven.demo")

public class Main {
	
    public static void main( String[] args )
    {
    	 AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(appconfig.class); 
    Person person=applicationContext.getBean("PersonBean",Person.class);
    Vehicle veh=applicationContext.getBean(Vehicle.class);
         System.out.println(veh.hashCode());
    	 System.out.println(person.getName());
    	 System.out.println(veh.getName());
    	 applicationContext.registerBean("Animal3",Animal.class,()->new Animal());
    	 System.out.println(person.getVehicle().hashCode()+"is vehicle associated with the person"+person.getName());
         System.out.println( "Hello World!" + applicationContext.getBean("Animal3",Animal.class)+applicationContext.getBean(Animal.class));
         System.out.println( "Hello World!" + applicationContext.getBean("name"));
         applicationContext.close();
    }
}
