package com.example.CustomAnnotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.example.CustomAnnotation.beans,com.example.CustomAnnotation.aspects")
public class ProjectConfig {
	

}
