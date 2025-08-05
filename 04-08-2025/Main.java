package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class Main {
  public static void main(String[] args) {
	  Vehicle v = new Vehicle();
	  v.setName("Ferrari");
	  System.out.println("Non-Spring Context Vehicle is : "+v.getName());
	  
	  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
	  
      Vehicle veh = context.getBean(Vehicle.class);
      System.out.println("Spring Context Vehicle is : " + veh.getName());
      
      String j =context.getBean(String.class);
      System.out.println("Spring Context Message is "+j);
      
      Integer num = context.getBean(Integer.class);
      System.out.println("Integer value from Spring Context is: " + num);
      context.close();
  }
}
