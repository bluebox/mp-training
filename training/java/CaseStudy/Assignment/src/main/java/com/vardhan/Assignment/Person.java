package com.vardhan.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
	@Autowired
	Vehicle vehicle;
	
	public void Human() {
		vehicle.Automotive();
	}

}
