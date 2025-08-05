package com.saketh.Advbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderPizza {
	@Autowired
	@Qualifier("vegPizza")
	Pizza pizza;
	void order() {
		pizza.bake();
	}
	
}
