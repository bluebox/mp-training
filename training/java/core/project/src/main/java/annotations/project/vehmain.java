package annotations.project;
import annotations.project.Vehicle;
import annotations.project.projectconfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class vehmain {
	public static void main(String[] args) {
		Vehicle vehicle = new Vehicle();
		vehicle.setName("bullet");
		System.out.println("Vehicle name form non-spring context is: " + vehicle.getName());
		var context = new AnnotationConfigApplicationContext(projectconfig.class);
		
		Vehicle veh = context.getBean(Vehicle.class);
		System.out.println("Vehicle name from Spring-context is : " + veh.getName());
		
		
		
	  String hello = context.getBean(String.class);
	  System.out.println("String value from spring context is: "+ hello);
//	  Integer num = context.getBean(Integer.class);
//	  System.out.println("String value form spring context is :" + num);
	
	
	}
	
	
	
	

}
