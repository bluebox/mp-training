package com.example.CustomAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.CustomAnnotation.beans.Vehicle;
import com.example.CustomAnnotation.config.ProjectConfig;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Vehicle v1=context.getBean(Vehicle.class);
        v1.playMusic(true);
        String[] beanNames = context.getBeanDefinitionNames();

     // Print bean names
     System.out.println("Beans registered in context:");
     for (String name : beanNames) {
         System.out.println(name);
     }
        context.close();
    }
}
