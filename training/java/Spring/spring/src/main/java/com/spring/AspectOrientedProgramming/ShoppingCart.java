package com.spring.AOP;

import org.springframework.stereotype.Component;

@Component 
public class ShoppingCart {

	public void checkout() {
		//logging
		//authentication and authorization
	
		System.out.println("checkout method from shopping cart called");
	}
	
	public void checkout2(String s) {
		System.out.println("checkout method 2 from shopping cart is called");
	}
}
