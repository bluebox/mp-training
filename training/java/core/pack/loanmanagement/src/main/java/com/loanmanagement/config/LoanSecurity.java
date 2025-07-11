//package com.loanmanagement.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//
//public class LoanSecurity {
//	    @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        http.authorizeHttpRequests(authz -> authz
//	        		.requestMatchers("/data/**").hasAnyRole("ADMIN")
//	                .anyRequest().permitAll())
//	            .csrf(csrf -> {
//					try {
//						csrf.disable()
//						.httpBasic(Customizer.withDefaults());
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				});
//	        return http.build();
//	    }
//	    
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	    @Bean
//	    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
//	        UserDetails admin = User.builder()
//	                			.username("admin")
//	                			.password(encoder.encode("admin123"))
//	                			.roles("ADMIN")
//	                			.build();
//	       
//	        return new InMemoryUserDetailsManager(admin);
//	    }
//	    
//}