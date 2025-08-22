package com.example10;

public class Regions {
	
    public Regions(){
        System.out.println("Regions bean created by Spring");
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello(){
        System.out.println(
            "Printing Hello from Component Regions Bean");
    }

    @Override
    public String toString(){
        return name;
    }
}