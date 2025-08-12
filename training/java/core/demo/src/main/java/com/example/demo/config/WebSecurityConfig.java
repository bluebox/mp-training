package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableWebSecurity
public class WebSecurityConfig  { 
    @Bean
   SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
	   http.authorizeHttpRequests((authorize)->authorize.requestMatchers("/add").authenticated().anyRequest().permitAll()).formLogin(Customizer.withDefaults())
	   .httpBasic(Customizer.withDefaults());
	   
	   return http.build();
   }
    
    @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
    	UserDetails u1=User.withUsername("gopi").password("{noop}gopi123").roles("USER").build();
    	return new InMemoryUserDetailsManager(u1);
    }
   
}
