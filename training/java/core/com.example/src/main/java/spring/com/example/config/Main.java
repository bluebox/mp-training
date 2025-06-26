package spring.com.example.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.com.example.beans.Person;
import spring.com.example.beans.Vehicle;
import spring.com.example.services.VehicleServices;

public class Main {
public static void main(String args[]) {
//	var context=new AnnotationConfigApplicationContext(AppConfiguration.class);
//	Person person=context.getBean(Person.class);
////	person.getVehicle().getVehicleservice().playMusic();
////	person.getVehicle().getVehicleservice().moveVehicle();
//	Vehicle v=context.getBean(Vehicle.class);
//	VehicleServices vs=v.getVehicleservice();
//	
//	System.out.println(person.getName()+" is using "+v.getName()+" has "+v.getVehicleservice().playMusic()
//			+" "+v.getVehicleservice().moveVehicle());
//	
		
	var context=new AnnotationConfigApplicationContext(AppConfiguration.class);
    System.out.println("Before retrieving the Person bean from the Spring Context");
    Person person = context.getBean(Person.class);
    System.out.println("After retrieving the Person bean from the Spring Context");
}
}
