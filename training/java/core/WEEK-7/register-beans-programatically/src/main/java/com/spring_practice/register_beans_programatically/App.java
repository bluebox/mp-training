package com.spring_practice.register_beans_programatically;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring_practice.register_beans_programatically.config.ProjectConfig;
import com.spring_practice.register_beans_programatically.interfaces.Animal;
import com.spring_practice.register_beans_programatically.pojos.Cat;
import com.spring_practice.register_beans_programatically.pojos.Dog;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Random random = new Random();
		int num = random.nextInt(50);

		Animal animal = null;

		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

		Supplier<Dog> dogSupplier = () -> {
			Dog dog = new Dog();
			dog.setName("dog");
			return dog;
		};

		Supplier<Cat> catSupplier = () -> {
			Cat cat = new Cat();
			cat.setName("dog");
			return cat;
		};

		if (num % 2 == 0) {
			context.registerBean("dog", Dog.class, dogSupplier);
		} else {
			context.registerBean("cat", Cat.class, catSupplier);
		}

		try {
			animal = context.getBean(Dog.class);
		} catch (NoSuchBeanDefinitionException e) {
			System.out.println("Error occurred for dog...");
		}
		try {
			animal = context.getBean(Cat.class);
		} catch (NoSuchBeanDefinitionException e) {
			System.out.println("Error occurred for cat...");
		}

		System.out.println("Random Number Selected is : " + num);
		System.out.println("Animal name is : " + animal.getName());
		System.out.println(animal.makeSound());
	}
}
