package com.example.springBootSample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailService() {
		UserDetails user = User.builder().username("vardhan").password(passwordEncoder().encode("password"))
				.roles("USER").build();

		UserDetails admin = User.builder().username("saketh").password(passwordEncoder().encode("password"))
				.roles("ADMIN", "USER").build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/", "/home", "/css/**", "/js/**").permitAll() 
	            .requestMatchers("/products/create", "/products/edit", "/products/delete").hasRole("ADMIN") 
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
	            .deleteCookies("JSESSIONID", "remember-me") 
                .invalidateHttpSession(true) 
                .clearAuthentication(true) 
	            .permitAll()
	        );

	    return http.build();
	}

}
