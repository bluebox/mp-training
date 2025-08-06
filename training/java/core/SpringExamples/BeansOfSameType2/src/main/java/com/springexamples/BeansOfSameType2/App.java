package com.springexamples.BeansOfSameType2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springexamples.beans.Bike;
import com.springexamples.config.ProjectConfig;

public class App {
    public static void main(String[] args) {
    	ApplicationContext cont=new AnnotationConfigApplicationContext(ProjectConfig.class);
    	//Bike bike=cont.getBean(Bike.class); works fine when we use primary keyword or else raise no unique bean found exception
    	Bike bike=cont.getBean("bike3" ,Bike.class);
    	System.out.println(bike.getName()+" Bike has "+bike.getCc()+"cc engine");
    }
}
