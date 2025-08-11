package annotations.project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class mainex3 {
	public static void main(String[] args ) {
		var context = new AnnotationConfigApplicationContext(projectconfigex3.class);
		vehex3 vehicle1 = context.getBean("zozo",vehex3.class);
		System.out.println("Vehicle name from spring context is :" + vehicle1.getName());
		vehex3 vehicle2 = context.getBean("zozocarr",vehex3.class);
		System.out.println("Vehicle name from spring context is :" + vehicle2.getName());
		vehex3 vehicle3 = context.getBean("zozoauto",vehex3.class);
		System.out.println("Vehicle name from spring context is :" + vehicle3.getName());
		
	}

}
