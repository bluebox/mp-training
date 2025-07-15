package com.example.vehicle.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.vehicle.service.UserService;

@Configuration
public class ProjectSecurityConfig {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public ProjectSecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	    
	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowedOriginPatterns(List.of("http://localhost:3000")); 
	    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    corsConfig.setAllowedHeaders(List.of("*"));
	    corsConfig.setExposedHeaders(List.of("role"));
	    corsConfig.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);

	    http.cors().configurationSource(source) 
	        .and()
	        .csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/login","/").permitAll()
	        .antMatchers("/customer/update", "/customer/delete", "/customer/show", "/vehicle/update", "/vehicle/showById",
	                "/policy/add", "/policy/showById", "/policy/report", "/policy/renew", "/policy/update",
	                "/policy/payDue", "/policy/dueDate", "/claim/add", "/claim/claimById", "/claim/claimByUser",
	                "/claim/claimReports", "/user/updatePassword", "/user/show","/vehicle/showByCustomerId","/policy/showByUser")
	        .hasAnyRole("ADMIN", "USER")
	        .antMatchers("/customer/**", "/user/**", "/vehicle/**", "/policy/**", "/claim/**")
	        .hasRole("ADMIN")
	        .and()
	        .formLogin(form -> form.loginProcessingUrl("/login")
	            .successHandler((request, response, authentication) -> {
	                response.setStatus(HttpServletResponse.SC_OK);
	                response.addHeader("role", authentication.getAuthorities().toString());
	            })
	            .failureHandler((request, response, exception) -> {
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	            })
	            .permitAll())
	        .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")               
	        		.invalidateHttpSession(true).permitAll())
	        .httpBasic();

	    return http.build();
	}



	@Bean
	InMemoryUserDetailsManager userDetailsManager() {
		ArrayList<UserDetails> details = new ArrayList<UserDetails>();
		for (com.example.vehicle.model.User user : userService.getAllUsers()) {
			details.add(User.withUsername(user.getUsername()).password(user.getPassword()).roles("USER").build());
		}
		details.add(User.withUsername("Eren").password(passwordEncoder.encode("Attack")).roles("ADMIN").build());
		return new InMemoryUserDetailsManager(details);
	}


}
