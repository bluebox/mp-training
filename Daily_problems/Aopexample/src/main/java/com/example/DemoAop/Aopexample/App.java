package com.example.DemoAop.Aopexample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App 
{
    public static void main( String[] args )
    {
    	
    	var context=new AnnotationConfigApplicationContext(AopConfig.class);
    	Cricketer e=context.getBean(Cricketer.class);
    	e.cricket();
    	System.out.println("................................................");
    	//e.cricketexception();
    	
    }
}
