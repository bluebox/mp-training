package com.springexamples.example3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.springexamples.beans.SmartPhone;
import com.springexamples.config.ProjectConfig;

public class App {
    public static void main(String[] args) {
    	ApplicationContext context=new AnnotationConfigApplicationContext(SmartPhone.class);
    	SmartPhone phone=context.getBean(SmartPhone.class);
    	phone.info();
    	((AbstractApplicationContext) context).close();
    }
}
