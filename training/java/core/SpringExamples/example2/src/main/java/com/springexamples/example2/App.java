package com.springexamples.example2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springexamples.beans.Student;
import com.springexamples.config.ProjectConfig;

public class App {
    public static void main(String[] args) {
    	
    	ApplicationContext context=new  AnnotationConfigApplicationContext(ProjectConfig.class);
    	Student student=context.getBean(Student.class);
    	student.displayInfo();
    }
}
