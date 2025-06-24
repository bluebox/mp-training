package com.example.assessment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.assessment.beans.Person;

import com.example.assessment.config.ProjectConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ProjectConfig.class);
        
        Person person =context.getBean(Person.class);
        person.setPassword("1st Bean");
        System.out.println("Name of the person = "+person.getName());
        System.out.println("Password of the person = "+person.getPassword());
        
        Person person2 =context.getBean(Person.class);
        person2.setPassword("2st Bean");
        System.out.println("Name of the person = "+person2.getName());
        System.out.println("Password of the person = "+person2.getPassword());
        
        System.out.println("Name of the person = "+person.getName());
        System.out.println("Password of the person = "+person.getPassword());
        
        
        
        
        
    
    }
    
    
    
}
