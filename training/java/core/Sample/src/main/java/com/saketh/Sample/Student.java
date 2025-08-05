package com.saketh.Sample;

import org.springframework.stereotype.Component;

@Component
public class Student {
	String name;
	String classname;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
}
