package com.example.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
@EnableMethodSecurity
 
public class SecurityConfig {
	@Bean
	UserDetailsService studentdetailsService(PasswordEncoder encoder) {
		UserDetails admin=User.withUsername("principal")
				.password(encoder.encode("prince")) .roles("ADMIN") .build();
		UserDetails user=User.withUsername("student1") .password(encoder.encode("student1")).roles("USER") .build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	/*
	 * @Bean AuthenticationProvider authenticationProvider(UserDetailsService
	 * userDetailsService) { DaoAuthenticationProvider provider = new
	 * DaoAuthenticationProvider();
	 * provider.setUserDetailsService(userDetailsService);
	 * provider.setPasswordEncoder(new BCryptPasswordEncoder()); // Or any other
	 * return provider; }
	 */
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
		.csrf(Customizer->Customizer.disable())
		.authorizeHttpRequests(request -> request.anyRequest().permitAll())
		.formLogin(Customizer.withDefaults())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
		.build();
	}
	
	@Bean PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
