package com.example.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
//		http.csrf().disable()
//        .authorizeRequests()
//        .anyRequest().authenticated()
//        .and()
//        .httpBasic();
		
		
//		http.csrf(customizer -> customizer.disable())
////		http.authorizeHttpRequests(request->request.anyRequest().authenticated());
//		.authorizeHttpRequests(request->request.anyRequest().permitAll())
////		http.authorizeHttpRequests(request->request.anyRequest().denyAll());
//		.formLogin(Customizer.withDefaults())
//		.httpBasic(Customizer.withDefaults());
//		withDefaultPasswordEncoder() is deprecated
		
		http.csrf(customizer->customizer.disable())
		.authorizeHttpRequests(authorize->authorize
				.requestMatchers("/reserve").authenticated()
				.anyRequest().permitAll())
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		
		UserDetails user1=User.withUsername("Raju").password("{noop}raju@123").roles("USER").build();
		UserDetails user2=User.withUsername("Raji").password("{noop}raji@321").roles("USER").build();
		UserDetails user3=User.withUsername("admin").password("{noop}password").roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user1,user2,user3);
	}
	
}
