package com.example;

abstract class Person {
    private String name;
    private int age;
    private String gender;
    private String dob;
    private double weight;
    private String dateOfJoining;

    public Person(String personName,String personGender,String personDob, int personAge,double personWeight,String personJoinDate) {
    	this.name = personName;
    	this.gender = personGender;
    	this.dob = personDob;
        this.age = personAge;
        this.weight = personWeight;
        this.dateOfJoining=personJoinDate;
    }

    public String getName() { 
    	return name; 
    }
    
    public String getGender() { 
    	return gender; 
    }
    
    public String getDOB() { 
    	return dob; 
    }
    
    public int getAge() { return age; }    
    
    public double getWeight() { 
    	return weight; 
    }
    
    public String getJoinDate() {
    	return dateOfJoining;
    }
    

    public abstract void showDetails();
}