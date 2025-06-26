package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.ProductConfig;
import com.example.dao.Speaker;
import com.example.dao.Tyres;
import com.example.dao.Vehicle;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
		Speaker s=context.getBean(Speaker.class);
		System.out.println(s.getName());
		Tyres t=context.getBean(Tyres.class);
		System.out.println(t.getName());
		Vehicle v=context.getBean(Vehicle.class);
		System.out.println(v.speaker().getName());
	}
}
