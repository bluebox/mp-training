package com.spring.beanScope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		
//		Employee emp1 = context.getBean(Employee.class);
//		Employee emp2 = context.getBean(Employee.class);
//		
//		System.out.println("Are employees same? " + (emp1 == emp2));
//		
//		System.out.println("-------------------");
		
		EmployeeController empController1 = context.getBean(EmployeeController.class);
		EmployeeController empController2 = context.getBean(EmployeeController.class);
		
		System.out.println("Are controllers same? " + (empController1 == empController2));
		
		System.out.println("-------------------");
		
		User user1 = context.getBean(User.class);
		User user2 = context.getBean(User.class);

		System.out.println("Are users same? " + (user1 == user2)); 

		System.out.println("-------------------");
		
		UserController controller1 = context.getBean(UserController.class);
		UserController controller2 = context.getBean(UserController.class);

		System.out.println("Are controllers same? " + (controller1 == controller2));
		
	}
}
