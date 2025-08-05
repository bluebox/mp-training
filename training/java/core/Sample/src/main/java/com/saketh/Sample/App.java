package com.saketh.Sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main(String[] args){
//    	 AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ProjectConfig.class);
//    	 Student std=ctx.getBean(Student.class);
//    	 std.setName("Saketh");
//    	 std.setClassname("B.Tech CSE");
//    	 System.out.println("Name: "+std.getName());
//    	 System.out.println("Class: "+std.getClassname());
    ApplicationContext ctx=new ClassPathXmlApplicationContext("Spring.xml");
    Car car=(Car)ctx.getBean("car");
//    System.out.println(car.);
    car.drive();
//    Vehicle vehicle=(Vehicle)ctx.getBean("bike");
//    	vehicle.drive();
//    	Tyre tyre=(Tyre)ctx.getBean("tyre");
//    System.out.println(tyre);
//    System.out.println(tyre.getBrand());
    }
    
}
