package com.spring_practice.eager_and_lazy_initialization_demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring_practice.eager_and_lazy_initialization_demo.beans.EagerBean;
import com.spring_practice.eager_and_lazy_initialization_demo.beans.LazyBean;
import com.spring_practice.eager_and_lazy_initialization_demo.config.ProjectConfig;

public class EagerAndLazyInitializationDemoApplication {

	public static void main(String[] args) {
		
		var context=new AnnotationConfigApplicationContext(ProjectConfig.class);
		
		System.out.println("Before getting beans");
		LazyBean lb=context.getBean(LazyBean.class);
		EagerBean eb=context.getBean(EagerBean.class);
		System.out.println("After getting beans");
	}

}
