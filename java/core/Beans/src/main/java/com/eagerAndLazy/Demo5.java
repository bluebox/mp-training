package com.eagerAndLazy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo5 {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(Person.class);
		Person p = context.getBean(Person.class);
		String s=context.getBean(String.class);
		System.out.println(p.eagerBean());
//		System.out.println(p.lazyBean());
	}
}
