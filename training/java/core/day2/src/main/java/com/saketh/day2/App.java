package com.saketh.day2;

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
    	Student std=ctx.getBean(Student.class);
    	std.setName("Vardhan");
    	System.out.println(std.getName());
    	Service srv = ctx.getBean(Service.class);
    	ctx.registerBean("saketh", RegisterClass.class);
//    	ctx.refresh();
    	RegisterClass rgobj=ctx.getBean("saketh",RegisterClass.class);
    	System.out.println(rgobj.count+""+rgobj);
    	srv.intermediate();
    	ctx.close();
    	
    }
}
