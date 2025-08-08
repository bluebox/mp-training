package com.example.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
	private String name;
	private int age;
	private State address;
	

//	public Person(String name, int age, State address) {
//		this.name = name;
//		this.age = age;
//		this.address = address;
//	}
//	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getAge() {
//		return age;
//	}
//	public void setAge(int age) {
//		this.age = age;
//	}
//	public State getAddress() {
//		return address;
//	}
//	public void setAddress(State address) {
//		this.address = address;
//	}
//	
	public enum State{
		AP,TS,TN;
	}
	

}
