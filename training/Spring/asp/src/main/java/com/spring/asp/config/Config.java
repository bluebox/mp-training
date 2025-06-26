package com.spring.asp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.spring.asp.beans","com.spring.asp.aspect"})
@EnableAspectJAutoProxy
public class Config {
	

}
