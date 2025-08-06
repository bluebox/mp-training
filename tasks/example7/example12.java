package com.dom.Springbasic.example7;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.dom.Springbasic.example7.Person;
import com.dom.Springbasic.example7.ProjectConfig;
import com.dom.Springbasic.example7.Vehicle;
public class example12 {
	public static void main(String[] args) {
		var context=new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person person=context.getBean(Person.class);
		System.out.println("Person name from Spring Context is: " + person.getName());
	    System.out.println("Vehicle that Person own is: " + person.getVehicle());
}
}
