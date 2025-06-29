package com.casestudy.spring.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/MainMenu").setViewName("home");
		registry.addViewController("/Issue").setViewName("Issue");
		registry.addViewController("/Books").setViewName("BooksHome");
		registry.addViewController("/Members").setViewName("MembersHome");
		registry.addViewController("/Records").setViewName("Records");
	}

}
