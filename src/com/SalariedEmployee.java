package com;

public class SalariedEmployee extends Employee {
    private double annualSalary;
    private boolean isRetired;

    public double getAnnualSalary() {
        return annualSalary;
    }

    public boolean isRetired() {
        return isRetired;
    }

    SalariedEmployee(double annualSalary, boolean isRetired) {
        super(1234, "12/12/2003");
        this.annualSalary = annualSalary;
        this.isRetired = isRetired;
    }
    
    SalariedEmployee(String name, String date, String endDate, int employeeId, String hireDate, int sal, boolean isRetired) {
    	super(name, date, endDate, employeeId, hireDate);
    	this.annualSalary = sal;
    	this.isRetired = isRetired;
    }

    void retire() {
        if(isRetired())
            System.out.println("I am retired on " + super.getEndDate());
        else System.out.println("Not yet retired!");
    }

    public static void main(String[] args) {
        SalariedEmployee salariedEmployee1 = new SalariedEmployee("Mourya", "07/02/2000", "10/04/2023", 12345, "07/10/2015", 600000, true);
        SalariedEmployee salariedEmployee2 = new SalariedEmployee(5_00_000, true);
        salariedEmployee1.retire();
        System.out.println(salariedEmployee1.getAge());
        salariedEmployee1.collectPay();
        
        salariedEmployee2.retire();
        System.out.println(salariedEmployee2.getAge());
        salariedEmployee2.collectPay();
    }
}