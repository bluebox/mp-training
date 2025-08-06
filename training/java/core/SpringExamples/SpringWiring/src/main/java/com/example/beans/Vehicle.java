package com.example.beans;

public class Vehicle {

    public Vehicle(){
        System.out.println("vehicle bean created by Spring");
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
            " Hello from Component Vehicle Bean");
    }

    @Override
    public String toString(){
        return "vehicle name is:"+name;
    }
}