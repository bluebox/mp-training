package com.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectSecurityConfig {
	
//	 @Bean
//	    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//	        // Permit All Requests inside the Web Application
//	        http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
//	                .formLogin(Customizer.withDefaults())
//	                .httpBasic(Customizer.withDefaults());
//
//	        // Deny All Requests inside the Web Application
//	            /*http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
//	                .formLogin(Customizer.withDefaults())
//	                .httpBasic(Customizer.withDefaults());*/
//
//	        return http.build();
//
//	    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
		http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
	        .formLogin(form -> form.defaultSuccessUrl("/index",true));
		
		//.permitAll());

//		http.csrf(csrf -> csrf.disable())
//        .authorizeHttpRequests(auth -> auth.anyRequest().denyAll());
    
	    return http.build();
	}

}
