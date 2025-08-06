package com.springexamples.my_spring_app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springexamples.beans.Chocolate;
import com.springexamples.beans.Student;
import com.springexamples.config.ProjectConfig;

public class App {
    public static void main(String[] args) {
    	ApplicationContext context =new AnnotationConfigApplicationContext(ProjectConfig.class);
    	Student s1= context.getBean(Student.class);
    	s1.displayInfo();
    	Chocolate chocolate=context.getBean(Chocolate.class);
    	System.out.println("the choclate name : "+chocolate.getName()+" "
    			+ "is of cost : "+chocolate.getPrice()+" and is of type : "+chocolate.getFlavour());
    }
}
