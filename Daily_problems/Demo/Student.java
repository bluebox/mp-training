package com.Springpractise.Demo;

import org.springframework.stereotype.Component;

@Component
public class Student {

	private String name="Ganesh";
	private int id=89;
	public Student() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + "]";
	}
	public String getMessage() {
		return "this is a good news ";
	}
	
}
