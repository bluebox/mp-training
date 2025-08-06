package com.Springpractise.Demo;



public class Person {
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	@jakarta.annotation.PostConstruct
	public void init() {
		System.out.println("hi hello this is from postconstruct");
	}
	@jakarta.annotation.PreDestroy
    public void cleanup() {
        System.out.println("MyService bean is being destroyed. Performing cleanup...");
    }
	

}
