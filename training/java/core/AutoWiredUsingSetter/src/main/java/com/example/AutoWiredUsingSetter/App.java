package com.example.AutoWiredUsingSetter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 AnnotationConfigApplicationContext cxt=new AnnotationConfigApplicationContext(Milk.class,Tea.class);
         Milk mlk=cxt.getBean(Milk.class);
        mlk.prepere();
        mlk.getTea().drink();
    }
}
