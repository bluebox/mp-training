
package com.medplus.Roles_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("Security from spring *********");
		http.csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults())
				.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/users/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/users/register").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/enrollments/requests").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/enrollments/requests").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/api/enrollments/getallactiveusers").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/enrollments/requests/*/accept").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/enrollments/requests/*/reject").hasRole("ADMIN")
						.requestMatchers("/api/enums").permitAll()
						.anyRequest().authenticated())
				.formLogin(AbstractHttpConfigurer::disable).httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
