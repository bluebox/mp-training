package com.Springpractise.Demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	var context=new AnnotationConfigApplicationContext(Practicedemoconfig.class);
//    	context.scan("com.Springpractise.Demo");
//    	   context.refresh();
   System.out.println(    	context.getBean("demo"));
   System.out.println(    	context.getBean("Hanuman",Person.class));
   Person p=context.getBean(Person.class);
   System.out.println(p);
   System.out.println(context.getBean("shiva"));
   
   Student s=context.getBean(Student.class);
   System.out.println(s.getMessage());
   context.registerBean(Vehicle.class,()->new Vehicle(),d->d.setPrimary(true));
   context.registerBean("motor", Vehicle.class,()->new Vehicle());
   Vehicle v=context.getBean(Vehicle.class);
   
   v.run();
   Vehicle v2=(Vehicle) context.getBean("motor");
   v2.run();
   Vehicle v3=(Vehicle) context.getBean("vehicle007");
   Car c=context.getBean(Car.class);
   c.getVehicle().run();
   Bus b=context.getBean(Bus.class);
   b.getVechile().run();
   b.drive();
   Taxi t=context.getBean(Taxi.class);
   Taxi t1=context.getBean(Taxi.class);
   t.road();
   if(v.hashCode()==v3.hashCode()) {
	   System.out.println(" Singleton Both Vehicles are equal");
	   
	   v.setV_name("ferari");
	   System.out.println("vechile name using another Vehicles  is "+v3.getV_name());
   }
   
   if(t.hashCode()!=t1.hashCode()) {
	   System.out.println("Prototype both taxis are not equal");
	   t.setCompany("ola");
	   System.out.println("taxi 1 company is ola and taxi 2 company name is "+t1.getCompany());
   }
   
   context.close();
    }
    
    
}
