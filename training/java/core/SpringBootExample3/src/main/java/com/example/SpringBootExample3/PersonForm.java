package com.example.SpringBootExample3;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public class PersonForm {
	
	@NotNull
	@Size(min=2,max=30)
	private String name;
	
	@NotNull
	@Min(18)
	private String age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "PersonForm [name=" + name + ", age=" + age + "]";
	}
}
