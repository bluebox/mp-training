package com.springexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.springexamples.beans.Connect;

@Configuration
@ComponentScan(basePackages = "com.springexamples.beans")
public class ProjectConfig {
	
	@Bean
	Connect connect() {
		Connect connect=new Connect();
		connect.setDriver("com.mysql.cj.jdbc.Driver");
		connect.setUrl("jdbc:mysql://localhost:3306/library_management_system");
		connect.setUser("root");
		connect.setPass("root");
		return connect;
	}
}
