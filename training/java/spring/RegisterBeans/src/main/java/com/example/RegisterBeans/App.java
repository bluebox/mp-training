package com.example.RegisterBeans;

import java.util.function.Supplier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.RegisterBeans.beans.Vehicle;
import com.example.RegisterBeans.beans.config.ProjectConfig;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Vehicle v1=new Vehicle();
        v1.setName("ferari");
        
        Supplier<Vehicle> ferariSup=()->v1;
        
        Vehicle v2=new Vehicle();
        v2.setName("thar");
        Supplier<Vehicle> tharSup=()->v2;
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        context.registerBean("A",Vehicle.class, ferariSup);
        context.registerBean("B",Vehicle.class, tharSup);
        
        Vehicle vb1=context.getBean("A",Vehicle.class);
        System.out.println(vb1.getName());
        
        Vehicle vb2=context.getBean("B",Vehicle.class);
        System.out.println(vb2.getName());
    }
}
