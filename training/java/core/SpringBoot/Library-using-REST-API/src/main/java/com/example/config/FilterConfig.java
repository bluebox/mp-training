package com.example.config;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.model.LoginDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@PropertySource("classpath:application.properties")
//@ConfigurationProperties(prefix = "my.app.users")
@Configuration
//@Validated
public class FilterConfig {
	@Bean
	SecurityFilterChain FilterChain(HttpSecurity http) throws Exception{
//		http.authorizeRequests().antMatchers("/main","/show","/showMember","/showIssue","/logout","/","/issueBook/*").hasAnyRole("user","admin")
		http.csrf().disable().
		authorizeRequests().antMatchers("/main","/show","/showMember","/showIssue","/logout","/").hasAnyRole("user","admin")
		.antMatchers("/issueBook/*").permitAll()
		.anyRequest().hasRole("admin")
		.and().formLogin()
//		.loginPage("/login")
//		.defaultSuccessUrl("/main",true)
//		.failureUrl("/error")
//		.permitAll()
//		.and().logout().logoutSuccessUrl("/logout")
//		.invalidateHttpSession(true)
//		.permitAll()
		.and().httpBasic();
		
		return http.build();
	}
	private HashMap<String, String> m1;
//	@Autowired
//	public void myMap(LoginDetails l) {
//		this.m1=l.getData();
//	}
	@Bean
	public InMemoryUserDetailsManager userDetails() {
//		if(!l.getL().isEmpty()) {
//			System.out.println(l.getL());
//		}
		System.out.println(m1);
		ArrayList<UserDetails> u=new ArrayList<UserDetails>();
		HashMap<String,String> l=new HashMap<String, String>();
		l.put("bhanu", "134rd");
		l.put("ram", "35343e");
		l.put("shiva", "23243e3");
		for(String i:l.keySet()) {
			UserDetails user = User.withDefaultPasswordEncoder().username(i).password(l.get(i)).roles("user").build();
			u.add(user);
		}
		l=new HashMap<String, String>();
		l.put("srinu", "43434");
		l.put("raghav", "er343");
		l.put("govind", "23343");
		for(String i:l.keySet()) {
			UserDetails user = User.withDefaultPasswordEncoder().username(i).password(l.get(i)).roles("admin").build();
			u.add(user);
		}
		return new InMemoryUserDetailsManager(u);
	}
	
}