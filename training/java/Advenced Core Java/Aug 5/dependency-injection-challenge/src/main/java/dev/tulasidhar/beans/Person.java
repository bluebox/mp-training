package dev.tulasidhar.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
	
	@Autowired
	public Vehicle vehicle;
	
	
}
