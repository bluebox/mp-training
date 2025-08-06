package com.springexamples.example5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springexamples.beans.Calculator;
import com.springexamples.beans.Garage;
import com.springexamples.beans.Person;
import com.springexamples.beans.Showroom;
import com.springexamples.config.ProjectConfig;

public class App {
    public static void main(String[] args) {
    	ApplicationContext context=new AnnotationConfigApplicationContext(ProjectConfig.class);
    	Showroom showroom=context.getBean(Showroom.class);
    	Person person=context.getBean(Person.class);
    	Garage garage=context.getBean(Garage.class);
    	showroom.info();
    	garage.info();
    	person.display();
    	
    	Calculator calc=context.getBean(Calculator.class);
    	System.out.println(calc.multiply(3, 4));
    }
}
