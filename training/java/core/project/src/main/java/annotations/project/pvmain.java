package annotations.project;
import annotations.project.pvehicle;
import annotations.project.pvprojectconfig;
import annotations.project.Person;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class pvmain {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(pvprojectconfig.class);
		Person person = context.getBean(Person.class);
		pvehicle vehicle = context.getBean(pvehicle.class);
		System.out.println("person name from spring context is : " + person.getName());
		System.out.println("vehicle name from spring context is : " + vehicle.getName());
		System.out.println("vehicle that person own is: "+ person.getVehicle());
		
		
	}
	

}
