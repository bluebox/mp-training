package annotations.project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class mainex2 {
	public static void main (String[] args) {
		var context = new AnnotationConfigApplicationContext(projectconfigex2.class);
		Vehicle veh = context.getBean("vehicle1",Vehicle.class);
		System.out.println("vehicle name form springContext is : " +veh.getName());
	
		
		
	}

}
