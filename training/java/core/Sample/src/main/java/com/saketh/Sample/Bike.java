package com.saketh.Sample;

import org.springframework.stereotype.Component;

@Component
public class Bike implements Vehicle {

	@Override
	public void drive() {
		System.out.println("Bike is driving");
	}

}
