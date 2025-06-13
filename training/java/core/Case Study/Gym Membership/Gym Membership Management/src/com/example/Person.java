package com.example;

import java.time.LocalDate;
import java.time.Period;

abstract class Person {
    private String name;
    private int age;
    private String gender;
    private LocalDate dob;
    private double weight;
    private LocalDate dateOfJoining;

    public Person(String personName,String personGender,LocalDate personDob,double personWeight,LocalDate personJoinDate) {
    	this.name = personName;
    	this.gender = personGender;
    	this.dob = personDob;
        this.age = Period.between(dob, LocalDate.now()).getYears();
        this.weight = personWeight;
        this.dateOfJoining=personJoinDate;
    }

    public String getName() { 
    	return name; 
    }
    
    public String getGender() { 
    	return gender; 
    }
    
    public LocalDate getDOB() { 
    	return dob; 
    }
    
    public int getAge() { return age; }    
    
    public double getWeight() { 
    	return weight; 
    }
    
    public LocalDate getJoinDate() {
    	return dateOfJoining;
    }
    

    public abstract void showDetails();
}