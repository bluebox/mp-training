package com.spring_practice.eager_and_lazy_initialization_demo.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyBean {
	public LazyBean() {
		System.out.println("Lazy Bean created");
	}
}
