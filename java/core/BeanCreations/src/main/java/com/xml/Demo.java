package com.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.xml.Person;

public class Demo {

	public static void main(String[] args) {
		 var context = new ClassPathXmlApplicationContext("beans.xml");
		 Person person = context.getBean(Person.class);
		 person.setId(10);
		 person.setName("Bhanu Prasadh");
		 System.out.println("Person ID : "+person.getId());
		 System.out.println("Person Name : "+person.getName());
		 System.out.println(person.hello());
	}

}
