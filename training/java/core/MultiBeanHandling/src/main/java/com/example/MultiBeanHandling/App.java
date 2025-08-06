package com.example.MultiBeanHandling;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	
    	// used qualifier
       ApplicationContext cxt=new AnnotationConfigApplicationContext(ServiceConfig.class);
       ServiceConsumer sc=cxt.getBean(ServiceConsumer.class);
       
       
       sc.useServices();
    }
}
