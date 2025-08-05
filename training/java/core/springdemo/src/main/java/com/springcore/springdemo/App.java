package com.springcore.springdemo;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

//       ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
//        Student student1=(Student)context.getBean("student1");
//        Student student2=(Student)context.getBean("student2");

//        System.out.println(student1);
//        System.out.println(student2);

        
    	
    	
    	
    
//    ApplicationContext context= new AnnotationConfigApplicationContext(CollegeConfig.class);
//	College college= context.getBean("CollegeBean", College.class);
//	college.test();
        
    	
    	
    	var context = new AnnotationConfigApplicationContext(CollegeConfig.class);
      
        Vehicle veh = context.getBean("vehicle1",Vehicle.class);
        System.out.println("Vehicle name from Spring Context is: " + veh.getName());
    }
}
