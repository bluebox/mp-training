package com.example.SpringExample3;

public class Vehicle {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public Vehicle()
	{
		System.out.println("this is vehicle");
	}
	public void print()
	{
		System.out.println("This is Vehicle method");
	}
}
