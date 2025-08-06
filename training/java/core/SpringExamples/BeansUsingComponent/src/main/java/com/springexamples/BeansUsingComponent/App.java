package com.springexamples.BeansUsingComponent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springexamples.beans.AnimalDesc;
import com.springexamples.config.ProjectConfig;

public class App {
    public static void main(String[] args) {
    	ApplicationContext cont=new AnnotationConfigApplicationContext(ProjectConfig.class);
    	AnimalDesc animal=cont.getBean(AnimalDesc.class);
    	animal.getAnimal().ability();
    	animal.getAnimal().makeSound();
    }
}
