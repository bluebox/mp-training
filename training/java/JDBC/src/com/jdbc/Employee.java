package com.jdbc;

public class Employee {
	
	private int id;
	private int age;
	private double salary;
	
	public Employee(int id, int age, double salary) {
		super();
		this.id = id;
		this.age = age;
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", age=" + age + ", salary=" + salary + "]";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
}
