package com.vardhan.Practice2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.vardhan.Practice2")
public class Config {
	
//	@Bean(name = "vardhan")
//	public Pojo pojo() {
//		Pojo pojo = new Pojo();
//		pojo.setName("Vardhan");
//		pojo.setAge(21);
//		return pojo;
//	}
//	
//	@Primary
//	@Bean(value = "saketh")
//	public Pojo pojo2() {
//		Pojo pojo = new Pojo();
//		pojo.setName("Saketh");
//		pojo.setAge(21);
//		return pojo;
//	}
//	
//	@Bean("tuslasi")
//	public Pojo pojo3() {
//		Pojo pojo = new Pojo();
//		pojo.setName("Tuslasi");
//		pojo.setAge(21);
//		return pojo;
//	}
//	
}
