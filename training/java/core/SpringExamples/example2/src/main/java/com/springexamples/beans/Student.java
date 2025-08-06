package com.springexamples.beans;

public class Student {
	
	private int id;
	private String name;
	
	public void displayInfo() {
		System.out.println("name : "+name+" id : "+id);
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
