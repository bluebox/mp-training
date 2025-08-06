package com.spring_practice.register_beans_programatically.pojos;

import com.spring_practice.register_beans_programatically.interfaces.Animal;

public class Dog implements Animal {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String makeSound() {
		return "bao bao";
	}

}
