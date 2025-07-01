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
		registry.addViewController("/Issue").setViewName("Issue/Issue");
		registry.addViewController("/Books").setViewName("Books/BooksHome");
		registry.addViewController("/Members").setViewName("Member/MembersHome");
		registry.addViewController("/Records").setViewName("Records/Records");
	}

}
