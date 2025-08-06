package com.example.MultiBeanHandling;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.example.MultiBeanHandling")
public class ServiceConfig {

	@Bean
	public Service Service1()
	{
		return new Service("service -1");
	}
	@Bean
	public Service Service2()
	{
		return new Service("service -2");
	}
}
