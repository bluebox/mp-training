package com.dom.Springbasic.example1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class Example2 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        // Vehicle veh = context.getBean(Vehicle.class);//Nouniquebeandefitionexception
        Vehicle veh = context.getBean("vehicle",Vehicle.class);
        System.out.println("Vehicle name from Spring Context is: " + veh.getName());
        }
}