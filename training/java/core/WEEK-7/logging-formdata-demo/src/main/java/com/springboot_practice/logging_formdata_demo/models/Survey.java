package com.springboot_practice.logging_formdata_demo.models;

import java.util.List;

public class Survey {
	
	private final String name;
	private final int age;
	private final String gmail;
	private final String phone;
	private final List<String> favpls;
	
	public Survey(String name, int age, String gmail, String phone, List<String> favpls) {
		this.name = name;
		this.age = age;
		this.gmail = gmail;
		this.phone = phone;
		this.favpls = favpls;
	}

	@Override
	public String toString() {
		return "Survey [name=" + name + ", age=" + age + ", gmail=" + gmail + ", phone=" + phone + ", favpls=" + favpls
				+ "]";
	}
	
}
