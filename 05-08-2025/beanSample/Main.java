package com.beanSample;

import java.util.function.Supplier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class Main {
  public static void main(String[] args) {
	  //Registering bean
	  Vehicle v = new Vehicle();
	  v.setType("AeroPlane");
	  Supplier<Vehicle> vSup=()->v;
	  
	  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configurate.class);
	  //bean from configuration class
	  Vehicle veh = context.getBean(Vehicle.class);
      System.out.println("Spring Context Vehicle is : " + veh.getType());
	  
	  context.registerBean("Aeroplane", Vehicle.class, vSup,bd->bd.setPrimary(true));
	  System.out.println("Registered bean:"+v.getType());
     
      //simple message
      String j =context.getBean(String.class);
      System.out.println("Spring Context Message is "+j);
      
      //simple number
      Integer num = context.getBean(Integer.class);
      System.out.println("Integer value from Spring Context is: " + num);
      context.close();
  }
}