package com.spring.xmlPractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle{

	@Autowired
	private Tyre tyre;
	
	public Tyre getTyre() {
		return tyre;
	}

	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}

	@Override
	public void drive() {
		System.out.println("car " + tyre);
	}

}
