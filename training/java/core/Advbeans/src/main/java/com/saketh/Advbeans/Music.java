package com.saketh.Advbeans;

import org.springframework.stereotype.Component;

@Component
public class Music {
	String name;
	public void play() {
		System.out.println(name + " is playing");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
