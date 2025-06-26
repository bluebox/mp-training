package com.eagerAndLazy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Person {
	@Bean
	public String eagerBean() {
//		System.out.println("Eager bean is initialized");
		return "Eager bean initialized";
	}
	@Bean
	public String lazyBean() {
//		System.out.println("Lazy bean initialized");
		return "lazy bean initialized";
	}
}
