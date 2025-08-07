package com.spring.Practice;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		Random rand = new Random();
		int num = rand.nextInt(10);

		Supplier<Vehicle> getAudi = () -> {
			Vehicle audi = new Vehicle();
			audi.setName("Audi");
			return audi;
		};

		Supplier<Vehicle> getHonda = () -> {
			Vehicle honda = new Vehicle();
			honda.setName("Honda");
			return honda;
		};

		System.out.println("Random number = " + num);

		if (num % 2 == 0) {
			context.registerBean("audi", Vehicle.class, getAudi);
		} else {
			context.registerBean("honda", Vehicle.class, getHonda);
		}

		Vehicle audi = null;
		Vehicle honda = null;
		
		try {
			audi = (Vehicle) context.getBean("audi");
		} catch (NoSuchBeanDefinitionException e) {
			System.out.println("Error while creating Audi vehicle");
		}
		try {
			honda = (Vehicle) context.getBean("honda");
		} catch (NoSuchBeanDefinitionException e) {
			System.out.println("Error while creating Honda vehicle");
		}
		
		System.out.println(audi.getName());
		System.out.println(honda.getName());
	}
}
