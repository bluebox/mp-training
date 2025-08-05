package com.saketh.Advbeans;

import org.springframework.stereotype.Component;

@Component
public class NonVegPizza implements Pizza {

	@Override
	public void bake() {
		System.out.println("baking Non Veg Pizza");
	}

}
