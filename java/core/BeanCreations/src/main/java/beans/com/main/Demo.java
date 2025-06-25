package beans.com.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import beans.com.bean.Person;
import beans.com.config.ProjectConfig;

public class Demo {
	public static void main(String[] args) {
		
		Person p = new Person();
		
		p.setName("Renu");
		System.out.println("Person name (without spring context) is : "+p.getName());
		
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		
		Person per = context.getBean(Person.class);
		System.out.println("Person ID (spring context) is : "+per.getId());

		System.out.println("Person name (spring context) is : "+per.getName());
		
		String hello = context.getBean(String.class);
		System.out.println("hello() is : "+hello);
	}
}
