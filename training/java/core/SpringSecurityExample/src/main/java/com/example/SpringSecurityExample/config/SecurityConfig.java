package com.example.SpringSecurityExample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

		security.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(request -> request.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return security.build();

	}

	@Bean
	public UserDetailsService userDetailsService() {

		UserDetails user1 = User.withDefaultPasswordEncoder().username("tarun").password("tarun@1728").roles("USER")
				.build();

		UserDetails user2 = User.withDefaultPasswordEncoder().username("rabi").password("ravi123").roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user1, user2);
	}

}
