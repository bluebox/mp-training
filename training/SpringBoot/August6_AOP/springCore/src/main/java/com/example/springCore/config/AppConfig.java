package com.example.springCore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.springCore.model.Student;


@ComponentScan(basePackages = "com.example.springCore")
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
	

}
	
	

