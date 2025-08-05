package dev.kaushik.autowiring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.kaushik.autowiring.beans.Person;
import dev.kaushik.autowiring.config.ProjectConfig;

public class CheckScope {

	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Person person1 = context.getBean(Person.class);
		System.out.println("person 1 hash code is " + person1.hashCode());
		Person person2 = context.getBean(Person.class);
		System.out.println("person 2 hash code is " + person2.hashCode());
		System.out.println("person 2 address is " + person2); // this gives hexadecimal equivalent to hashCode
		context.close();
	}

}
