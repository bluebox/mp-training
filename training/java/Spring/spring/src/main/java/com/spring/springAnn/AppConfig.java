package com.spring.springAnn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Spring Configuration annotation indicates that the class has @Bean definition methods. 
//So Spring container can process the class and generate Spring Beans to be used in the application.

@Configuration
@ComponentScan(basePackages="com.spring.springAnn")
public class AppConfig {

	
//	@Bean annotation lets Spring know that it needs to call this method when it initializes its context and adds the returned value to the context.
    
//	@Bean
//	public Samsung getPhone() {
//		return new Samsung();
//	}
//	
//	
//	@Bean
//	public MobileProcessor getProcessor() {
//		return new Snapdragon();
//	}
	
}
