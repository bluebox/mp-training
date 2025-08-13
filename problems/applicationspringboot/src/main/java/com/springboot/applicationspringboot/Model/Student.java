package com.springboot.applicationspringboot.Model;

import org.springframework.stereotype.Component;

@Component
public class Student {
     private String name;
     private int rollno;
     private int Standard;
     
     public Student(String name, int rollno, int standard) {
		this.name = name;
		this.rollno = rollno;
		Standard = standard;
	}
	 public Student() {
		
	}
	 @Override
	public String toString() {
		return "Student [name=" + name + ", rollno=" + rollno + ", Standard=" + Standard + "]";
	}
	 public String getName() {
		return name;
	}
	 public void setName(String name) {
		 this.name = name;
	 }
	 public int getRollno() {
		 return rollno;
	 }
	 public void setRollno(int rollno) {
		 this.rollno = rollno;
	 }
	 public int getStandard() {
		 return Standard;
	 }
	 public void setStandard(int standard) {
		 Standard = standard;
	 }
	
	
	
}
