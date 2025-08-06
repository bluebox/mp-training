package com.example.PreDestroyPostConstruct;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext cnt=new AnnotationConfigApplicationContext (AppConfig.class);
    	frutControl fc=cnt.getBean(frutControl.class);
    	fc.getFruits();
    	//cnt.close();
    }
}
