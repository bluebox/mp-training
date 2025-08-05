package com.springcore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * -create maven project
 * -adding dependencies (spring core , spring context)
 * -creating beans (java pojos)
 * -creating configuration file (config.xml)
 * -using setter injection 
 * -Main class:which can pull the object and use it
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Student student1=(Student)context.getBean("student1");
        Student student2=(Student)context.getBean("student2");
        System.out.println(student1);
        System.out.println(student2);
    }
}
