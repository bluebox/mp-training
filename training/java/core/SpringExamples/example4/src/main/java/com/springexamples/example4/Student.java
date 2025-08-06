package com.springexamples.example4;

public class Student {

	private int id;
	private String name;
	
	public void display() {
		System.out.println("name : "+name+"\nid : "+id);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
