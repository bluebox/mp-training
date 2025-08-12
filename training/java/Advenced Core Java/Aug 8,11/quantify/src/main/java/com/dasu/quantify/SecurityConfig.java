package com.dasu.quantify;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
			
			http.csrf(csrf->csrf.disable());
		
			http.authorizeHttpRequests(t -> t.requestMatchers("/aboutus").authenticated()
				.requestMatchers("/signup").permitAll()
				.requestMatchers("/styles/**").permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/home").permitAll()
				.requestMatchers("/track").authenticated()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/error").permitAll()
				.requestMatchers("/logout").permitAll()
				.requestMatchers("/admin").hasRole("ADMIN"))
				.formLogin(custom -> 
						custom.loginPage("/login")
						.defaultSuccessUrl("/track",true)
						.failureUrl("/login")
						.permitAll());			
            return http.build();
    }
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
								.username("dasu")
								.password("dasu")
								.roles("USER")
								.build();
		
		
		UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("USER", "ADMIN")
                .build();
		
		return new InMemoryUserDetailsManager(user,admin);
				
	}
}
