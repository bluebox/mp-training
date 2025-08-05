package com.saketh.Advbeans;

import org.springframework.stereotype.Component;

@Component
public class VegPizza implements Pizza {
	
	@Override
	public void bake() {
		System.out.println("Baking veg pizza");
	}

}
