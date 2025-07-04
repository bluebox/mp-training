package com.gym.classes;

//abstraction
public abstract class Person {
	String name;
	int age;
	int height;
	int weight;
	
	public abstract void showDetails();
	
	public Person(String name, int age) {
		this(name,age,0,0);
	}
	
	public Person(String name, int age, int height, int weight) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	
}
