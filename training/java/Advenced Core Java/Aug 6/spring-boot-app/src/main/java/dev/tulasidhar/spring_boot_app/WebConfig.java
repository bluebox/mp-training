package dev.tulasidhar.spring_boot_app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/homestatic").setViewName("forward:/home.html");
		WebMvcConfigurer.super.addViewControllers(registry);
		
	}
}
