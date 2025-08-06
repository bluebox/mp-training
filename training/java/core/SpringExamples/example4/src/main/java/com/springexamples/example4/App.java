package com.springexamples.example4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
    	
    	ApplicationContext context=new ClassPathXmlApplicationContext("com/springexamples/example4/beans.xml");
    	Student s1=(Student) context.getBean("student1");
    	s1.display();
    	    }
}
