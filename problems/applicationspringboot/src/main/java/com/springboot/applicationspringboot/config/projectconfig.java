package com.springboot.applicationspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class projectconfig {
	 @Bean
	    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

	        // Permit All Requests inside the Web Application
//	        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated())  // for any request to permitall no login provided,authenticated login page provided
//	                .formLogin(Customizer.withDefaults()) // to give form login as usual
//	                .httpBasic(Customizer.withDefaults()); // basic authentication providing  for working with the postman
	        
	        
//	        http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())  // for any request to denied no login page provided
//            .formLogin(Customizer.withDefaults()) // to give form login as usual
//            .httpBasic(Customizer.withDefaults()); // basic authentication al;gorithm provided for working with the postman
//	        
	        
		 http.csrf((csrf) -> csrf.disable())
		 .authorizeHttpRequests(requests->requests.requestMatchers("/home").authenticated()
				 .requestMatchers("/**").permitAll())
				 .formLogin(Customizer.withDefaults())
				 .logout(lc->lc.invalidateHttpSession(true).permitAll())
				 .httpBasic(Customizer.withDefaults());
		 	 
		 
	        return http.build();   // customised filter chain is being created and returned under configuration class

}
	 
	 @SuppressWarnings("deprecation")
	@Bean       // ffor inmemory samples for testing
	 public InMemoryUserDetailsManager Userdetails() {
		 UserDetails user=User.withDefaultPasswordEncoder()
				    .username("user")
				    .password("123")
				    .roles("USER")
				    .build();
		 
		 UserDetails admin=User.withDefaultPasswordEncoder()
				     .username("admin")
				     .password("123")
				     .roles("USER","ADMIN")
				     .build();
		 
		 return new InMemoryUserDetailsManager(user, admin);
	 }
	 
	 

}