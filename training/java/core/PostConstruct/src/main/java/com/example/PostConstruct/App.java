package com.example.PostConstruct;

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
        ApplicationContext cxt=new AnnotationConfigApplicationContext(Caller.class);
        Caller clr=cxt.getBean(Caller.class);
        
        
        // here when we call
        clr.liftcall();

    }
}
