package com.vardhan.Practice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
  public static void main(String[] args) {
	  
	  ApplicationContext context = new ClassPathXmlApplicationContext("vehicle.xml");
	 // Vehicle vehicle = (Vehicle) context.getBean("bike");
	 // vehicle.Drive();
	  
//	  Tyre tyre =(Tyre) context.getBean("tyre");
//	  System.out.println(tyre);
	  
	  Car obj = (Car) context.getBean("car");
	  obj.Drive();
  }
}
