package com.exampletwo;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CarMain {

	public static void main(String[] args) {
		  try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configurate.class)) {
			CarType car = context.getBean(CarType.class);
			  System.out.println("Spring Default CarType is : ");
			  car.typeOfCar();
			  Ferrari bmw=context.getBean("ferrari1",Ferrari.class);
			  System.out.println("Intentionally called car: ");
			  bmw.typeOfCar();
		  } catch (BeansException e) {
			e.printStackTrace();
		  }
	      
	}
}
