package com;

public class Employee extends Worker {
    private long employeeId;
    private String hireDate;

    Employee() {
        System.out.println("Default constructor in Employee called...!");
    }    

//    Employee(long employeeId) {
//    	this(employeeId, "20/04/2020");
//    	
//    }
    
//    Employee(String name, String date, String endDate, int employeeId, String hireDate) {
//    	super(name, date, endDate);
//    	this.employeeId = employeeId;
//    	this.hireDate = hireDate;
//    }
//
//    Employee(long employeeId, String hireDate) {
//    	this.employeeId = employeeId;
//    	this.hireDate = hireDate;
//    }
}