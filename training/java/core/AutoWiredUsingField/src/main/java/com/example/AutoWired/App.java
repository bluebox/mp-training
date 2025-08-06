package com.example.AutoWired;

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
        ApplicationContext cxt=new AnnotationConfigApplicationContext(Development.class,Laptop.class);
        Development dvp=cxt.getBean(Development.class);
        dvp.build();
        dvp.getHp().compile();

    }
}
