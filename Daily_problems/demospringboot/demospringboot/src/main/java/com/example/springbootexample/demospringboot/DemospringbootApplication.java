package com.example.springbootexample.demospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemospringbootApplication {

	public static void main(String[] args) {
		System.out.println("this is for test ");
		System.out.println("Hello Hell");
		
		SpringApplication.run(DemospringbootApplication.class, args);
		
		
	}

}
