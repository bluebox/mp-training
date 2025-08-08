package com.springexamples.DynamicBeans;

import java.util.Scanner;
import java.util.function.Supplier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springexamples.beans.Food;

public class App {
    public static void main(String[] args) {
    	
    	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Food.class);
    	Scanner sc=new Scanner(System.in);
    	String name=sc.nextLine();
    	Supplier foodu=()->{
    		Food food=new Food();
    		food.setName(name);
    		return food;
    	};
    	context.registerBean(name,Food.class,foodu);
    	Food food=context.getBean(name,Food.class);
    	System.out.println(food.getName());
    }
}
