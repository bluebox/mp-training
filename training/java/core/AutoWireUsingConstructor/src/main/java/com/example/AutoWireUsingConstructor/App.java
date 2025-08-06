package com.example.AutoWireUsingConstructor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext acc= new AnnotationConfigApplicationContext(Son.class,Father.class);
    	Father ftr=acc.getBean(Father.class);
//    	ftr.Scold();
    	ftr.getSon().Cry();
    }
}
