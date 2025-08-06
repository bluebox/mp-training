package com.example.springCore.model;

import org.springframework.stereotype.Component;

@Component
public class Student {
	private String name;
	private int age;
	private  final String course="CSE";
	
	
	public 	Student(Student s1) {
		System.out.println(s1.getAge());
		System.out.println(s1.getName());
	}

	public Student() {
		
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}


	public String getCourse() {
		return course;
	}
}
