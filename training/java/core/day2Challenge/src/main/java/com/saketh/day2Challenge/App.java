package com.saketh.day2Challenge;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(ProjectConfig.class);
    	Person person=ctx.getBean(Person.class);
    	person.personVehicle();
    }
}
