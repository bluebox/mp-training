package com.springexamples.DynamicBeans;

import java.util.function.Supplier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springexamples.beans.Food;

public class App {
    public static void main(String[] args) {
    	
    	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Food.class);
    	Supplier Biryani=()->{
    		Food food=new Food();
    		food.setName("Biryani");
			return food;
    	};
    	
    	Supplier Pasta=()->{
    		Food food=new Food();
    		food.setName("Chicken Pasta");
    		return food;
    	};
    	
    	int num=(int)Math.random();
    	if((100%10)<5) {
    		context.registerBean("Biryani",Food.class,Biryani);
    	}
    	else {
    		context.registerBean("Pasta",Food.class,Pasta);
    	}
    	
    	Food food=context.getBean("Biryani",Food.class);
    	System.out.println(food.getName());
    }
}
