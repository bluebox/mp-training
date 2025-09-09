package com.medplus.exptracker;

import java.util.Arrays;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.medplus.exptracker.Service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserService userService;

	 
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable());
		http.cors();
		http.authorizeHttpRequests(auth->{
			auth
				.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/logout").permitAll()
				
				.requestMatchers("/api/expenses/categories").permitAll()
				
				.requestMatchers("/api/employee/**").hasRole("EMPLOYEE")
				
				.requestMatchers("/api/manager/expenses/**").hasRole("MANAGER")

				.requestMatchers("/api/admin/**").hasRole("ADMIN");
		})
		.userDetailsService(userService)
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		.httpBasic(Customizer.withDefaults())
		
		.logout(logout->logout
			.logoutUrl("/logout")
			.deleteCookies("JSESSIONID"));
		
		return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
	    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS" ,"PUT"));
	    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	    corsConfig.setAllowCredentials(true);

	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);
	    return source;
	}

}
