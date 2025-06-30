package com.example.config;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class FilterConfig {
	@Bean
	SecurityFilterChain FilterChain(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/main","/show","/showMember","/showIssue").permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
		.and().httpBasic();
		return http.build();
	}
	@Bean
	public InMemoryUserDetailsManager userDetails() {
		HashMap<String, String> l=new HashMap<String, String>();
		l.put("bhanu", "134rd");
		l.put("ram", "35343e");
		l.put("Shiva", "23243e3");
		ArrayList<UserDetails> u=new ArrayList<UserDetails>();
		for(String i:l.keySet()) {
			UserDetails user = User.withDefaultPasswordEncoder().username(i).password(l.get(i)).roles("user").build();
			u.add(user);
		}
		return new InMemoryUserDetailsManager(u);
	}
}
