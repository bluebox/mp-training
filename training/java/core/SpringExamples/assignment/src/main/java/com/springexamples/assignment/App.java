package com.springexamples.assignment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springexamples.beans.Person;
import com.springexamples.config.ProjectConfig;

public class App {
    public static void main(String[] args) {
        
    	ApplicationContext context=new AnnotationConfigApplicationContext(ProjectConfig.class);
    	Person person=context.getBean(Person.class);
    	person.move();
    	person.sound();
    }
}
