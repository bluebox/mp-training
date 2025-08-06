package com.example.springcorePractice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.springcorePractice.configurations.AppConfig;
import com.example.springcorePractice.model.Car;

public class App 
{
	
	public static class MainApp {
	    public static void main(String[] args) {
	        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	        Car car = context.getBean(Car.class);
	        car.startCar();

	        context.close();
	    }
	}
  


}
