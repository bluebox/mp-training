package com.spring_practice.eager_and_lazy_initialization_demo.beans;

import org.springframework.stereotype.Component;

@Component
public class EagerBean {
	public EagerBean() {
		System.out.println("Eager Bean created");
	}
}
