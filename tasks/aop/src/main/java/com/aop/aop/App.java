package com.aop.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.beans.Student;
import com.config.ProjectConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Student student = context.getBean(Student.class);
        student.setName("John");
        student.setAge(25);

        student.getName();
        student.getAge();

        try {
            student.printThrowException();
        } catch (Exception e) {
            // Suppressed for demonstration
        	System.out.println("exception occured");
        }

    }
}
