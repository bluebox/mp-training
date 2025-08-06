package com.springexamples.BeansWithXml2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springexamples.beans.Dogs;

public class App {
    public static void main(String[] args) {
    	ApplicationContext context=new ClassPathXmlApplicationContext("com/springexamples/config/config.xml");
    	Dogs dog1=(Dogs) context.getBean("Dog1");
    	System.out.println(dog1.getBreed()+"'s usually weigh around : "+dog1.getWeight()+" and are "+dog1.getSize());
    }
}
