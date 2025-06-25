package com.example.PrototypeScope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.PrototypeScope.beans.Vechicle;
import com.example.PrototypeScope.config.ProjectConfig;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ProjectConfig.class);
        
        Vechicle v1=context.getBean(Vechicle.class);
        v1.setName("mani");
        v1.setPassword("mani");
        
        Vechicle v2=context.getBean(Vechicle.class);
        System.out.println(v1.hashCode());
        System.out.println(v2.hashCode());
        
    
    }
}
