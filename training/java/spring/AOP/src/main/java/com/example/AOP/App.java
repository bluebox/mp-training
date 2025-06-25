package com.example.AOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.AOP.beans.Vehicle;
import com.example.AOP.config.ProjectConfig;

/**
 * Hello world!
 */

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        
        Vehicle v1=context.getBean(Vehicle.class);
        v1.setName("mani");
        v1.move(true);
        v1.playMusic(true);
        
       //v1.playMusic(false);
        //v1.move(false);
        
        
        
        
        context.close();
        
    }
}
