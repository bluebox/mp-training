package com.example.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registor) {
		registor.addViewController("/contact").setViewName("Contact");
	}

}
