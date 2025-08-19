package com.spring_practice.basic_spring_security_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
			.requestMatchers("/").permitAll()
			.requestMatchers("/securedPage").authenticated()
			.and().formLogin().defaultSuccessUrl("/").permitAll()
			.and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
			.and().httpBasic();
		return http.build();
		
	}
}
