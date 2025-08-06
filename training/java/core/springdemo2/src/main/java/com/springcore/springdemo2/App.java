package com.springcore.springdemo2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
     context.scan("com.springcore.springdemo2");
     context.refresh();
     ComponentDemo componentDemo = context.getBean(ComponentDemo.class);
     componentDemo.demoFun();
     context.close();
    }
}
