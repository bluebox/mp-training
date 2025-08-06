package com.dom.Springbasic.example5;



public class Vehicle {
	public Vehicle() {
		System.out.println("vehicle bean created by spring");
	}

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello(){
        System.out.println("Printing Hello from Component Vehicle Bean");
    }

	@Override
	public String toString() {
		return "Vehicle [name=" + name + "]";
	}
    
}