package com.example.springbootexample.demospringboot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain secfilterchain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable());
//		http.authorizeHttpRequests(req->req.anyRequest().denyAll())
//		.formLogin(Customizer.withDefaults());
		http.authorizeHttpRequests(req->req
				.requestMatchers("/","/Home").authenticated()
//				.requestMatchers("/Home/person").permitAll()
				.anyRequest().authenticated()
				)
		
		.formLogin(Customizer.withDefaults())
		.logout(lc->lc.invalidateHttpSession(true).permitAll())
		.httpBasic(Customizer.withDefaults());
		
		
		return http.build();
	}
	
	
	@Bean 
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user=User.withDefaultPasswordEncoder()
							.username("ganesh")
							.password("god")
							.roles("USER")
							.build();
		UserDetails admin=User.withDefaultPasswordEncoder()
				.username("shiva")
				.password("destroyer")
				.roles("ADMIN","USER")
				.build();
		return new InMemoryUserDetailsManager(user,admin);
	}
	
}
