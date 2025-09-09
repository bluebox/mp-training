package com.vardhan.Practice3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Student {
	private String name = "Vardhan";
	
	
	@Autowired
	public Student(Marks marks) {
		System.out.println("Student Bean");
		this.name = name;
		
	}
	
	private Marks marks;
	
	public String getName() {
	return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Marks getMarks() {
		return marks;
	}
	
	public void setMarks(Marks marks) {
	this.marks = marks;
	}
	
}
