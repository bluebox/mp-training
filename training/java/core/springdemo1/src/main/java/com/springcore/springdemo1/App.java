package com.springcore.springdemo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 var context = new AnnotationConfigApplicationContext(VehicleConfig.class);

         Vehicle veh1 = context.getBean("audiVehicle",Vehicle.class);
         System.out.println("Vehicle name from Spring Context is: " + veh1.getName());

         Vehicle veh2 = context.getBean("hondaVehicle",Vehicle.class);
         System.out.println("Vehicle name from Spring Context is: " + veh2.getName());

         Vehicle veh3 = context.getBean("ferrariVehicle",Vehicle.class);
         System.out.println("Vehicle name from Spring Context is: " + veh3.getName());
         
         
         
         
         // we can remove  name and just call with vehicle class then we have to keep primary on  the particular been
         
         
         
    }
}
