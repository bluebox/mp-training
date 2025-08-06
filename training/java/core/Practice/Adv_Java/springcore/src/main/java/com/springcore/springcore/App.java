package com.springcore.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.sun.javafx.css.StringStore;

public class App 
{
    public static void main( String[] args )
    {
		/*
		 * System.out.println( "Hello World!" ); ApplicationContext context = new
		 * ClassPathXmlApplicationContext("configure.xml"); Student
		 * student1=(Student)context.getBean("student1"); System.out.println(student1);
		 */
    	ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
    	Teacher t1 = context.getBean("teacher1",Teacher.class);
    	Person p1=context.getBean(Person.class);
    	t1.setPerson(p1);
		/*
		 * Teacher t2 = context.getBean("teacher2",Teacher.class); Teacher t3 =
		 * context.getBean("3",Teacher.class); Teacher t4 =
		 * context.getBean("teacher4",Teacher.class); Teacher t5 =
		 * context.getBean("5",Teacher.class); Teacher t6 =
		 * context.getBean("6",Teacher.class); Teacher t7 =
		 * context.getBean(Teacher.class);
		 */
    	//Student s1 = context.getBean(Student.class);
    	//System.out.println(s1);
    	//GenericApplicationContext context1 = new GenericApplicationContext();
    	//Person p1=new Person();
    	//context1.registerBean("teachernew", Teacher.class, ()-> {return new Teacher(10,"sportsTeacher","Sports",p1);});
    	//context1.refresh();
    	//Teacher t8 = context1.getBean("teachernew",Teacher.class);
     	System.out.println(t1);
		/*
		 * System.out.println(t2); System.out.println(t3); System.out.println(t4);
		 * System.out.println(t5); System.out.println(t6); System.out.println(t7);
		 */
    	//System.out.println(t8);
    }
}
