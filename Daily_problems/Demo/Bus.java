package com.Springpractise.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class Bus {
	
	private Vehicle vechile;
	public void drive() {
		System.out.println("bus is moving");
		
	}
	public Vehicle getVechile() {
		return vechile;
	}
	@Autowired
	@Qualifier("vehicle007")
	public void setVechile(Vehicle vechile) {
		this.vechile = vechile;
	}
	

}
