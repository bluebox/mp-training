package com.maven.demo;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Lazy
public class Animal {
   private  String color;
   private String type;
   public String getColor() {
	return color;
   }
   public void setColor(String color) {
	this.color = color;
   }
   public String getType() {
	return type;
   }
   public void setType(String type) {
	this.type = type;
   }
   public Animal(String color, String type) {
	this.color = color;
	this.type = type;
   }
   public Animal() {
	// TODO Auto-generated constructor stub
}
   @Override
   public String toString() {
	return "Animal [color=" + color + ", type=" + type + "]";
   }
   
   
   
   
}
