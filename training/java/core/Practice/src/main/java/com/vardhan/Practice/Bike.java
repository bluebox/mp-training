package com.vardhan.Practice;

import org.springframework.stereotype.Component;

@Component
public class Bike implements Vehicle {
	
	public void Drive() {
		
		System.out.println("The bike ride is the best");
		
	}

}
