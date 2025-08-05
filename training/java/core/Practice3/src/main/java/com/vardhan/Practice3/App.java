package com.vardhan.Practice3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
  public static void main(String[] args) {
    
	  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
	  Student student = context.getBean(Student.class);
	  Marks marks = context.getBean(Marks.class);
	  System.out.println(student.getName());
	  System.out.println(marks.getName());
	  System.out.println(student.getMarks());
  }
}
