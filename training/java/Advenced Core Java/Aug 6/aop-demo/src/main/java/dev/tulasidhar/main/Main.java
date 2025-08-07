package dev.tulasidhar.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.tulasidhar.beans.Person;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
		Person person = context.getBean(Person.class);
		person.getHeart().pumpBlood();
		
		System.out.println("-".repeat(20));
		ApplicationContext context2 = new AnnotationConfigApplicationContext(SimpleConfig.class);
		Box box = context2.getBean(Box.class);
		box.openBox();
	}
}
