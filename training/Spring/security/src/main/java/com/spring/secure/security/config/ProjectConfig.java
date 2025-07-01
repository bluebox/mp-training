package com.spring.secure.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
	
	@Bean
	SecurityFilterChain secure(HttpSecurity security) throws Exception {
		security.authorizeHttpRequests(
				n -> n.anyRequest().permitAll())
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());
		return security.build();
		
	}
}
