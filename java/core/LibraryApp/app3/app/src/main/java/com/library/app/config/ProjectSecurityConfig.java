package com.library.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {


		http.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests((requests) -> requests.antMatchers("/", "/home").authenticated()
						.antMatchers("/member/**").permitAll()
						.antMatchers("/book/**").permitAll()
						.antMatchers("/issue/**").permitAll()
						.antMatchers("/assets/**").permitAll())
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	@Bean
	InMemoryUserDetailsManager userDetailsManager() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("Jai")
				.password("123")
				.roles("USER")
				.build();
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("Raj")
				.password("321")
				.roles("USER", "ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user,admin);

	}

}

