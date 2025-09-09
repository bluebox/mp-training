package com.vardhan.springbootwiththymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
 	}
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails owner = User.builder()
				.username("saketh")
				.password(encoder.encode("saketh"))
				.roles("OWNER")
				.build();
				
		UserDetails user = User.builder()
				.username("vardhan")
				.password(encoder.encode("vardhan"))
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(user, owner);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	        	.requestMatchers("/","/login").permitAll()
	            .requestMatchers("/products/**").hasRole("OWNER")
	        	.requestMatchers("/products/**").authenticated()
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login")     
	            .loginProcessingUrl("/perform_login")
	            .defaultSuccessUrl("/products", true)
	            .failureUrl("/login?error=true")
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login?logout=true")
	            .deleteCookies("JSESSIONID","remember-me")
	            .clearAuthentication(true)
	            .invalidateHttpSession(true)
	            .permitAll()
	        );
	    return http.build();
	}


}