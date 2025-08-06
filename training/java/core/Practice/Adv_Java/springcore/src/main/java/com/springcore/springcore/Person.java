package com.springcore.springcore;

public class Person {
	private String personName;
	private int age;
	private String gender;
	public Person() {
		this.personName = "person";
		this.age = 20;
		this.gender = "M";
	}
	public Person(String personName, int age, String gender) {
		super();
		this.personName = personName;
		this.age = age;
		this.gender = gender;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
