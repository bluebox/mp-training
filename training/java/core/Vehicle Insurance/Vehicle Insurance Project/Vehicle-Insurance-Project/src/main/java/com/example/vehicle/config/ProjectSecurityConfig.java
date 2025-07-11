package com.example.vehicle.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.vehicle.service.UserService;

@Configuration
public class ProjectSecurityConfig {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	public ProjectSecurityConfig(UserService userService,PasswordEncoder passwordEncoder) {
		this.userService=userService;
		this.passwordEncoder=passwordEncoder;
	}
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.cors(Customizer.withDefaults())
//		.authorizeRequests().antMatchers("/customer/add","/customer/showAll","/vehicle/add","/vehicle/showAll","/policy/showAll","/policy/dueDate","/policy/allRequestedPolicies","/policy/updatePolicyRequested","/claim/allClaims","/claim/allIntiatedClaims","/claim/approveClaim","/user/add","/user/showAll").hasRole("ADMIN").and()
//		.authorizeRequests().anyRequest().hasRole("USER")
		.authorizeRequests().anyRequest().permitAll()
		.and().formLogin()
		.and().httpBasic();
//		http.csrf((csrf) -> csrf.ignoringAntMatchers("/api/**"))
//				.authorizeHttpRequests((requests) -> requests.antMatchers("/**").permitAll())
//				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();
	}
//
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//	    CorsConfiguration configuration = new CorsConfiguration();
//	    configuration.setAllowedOrigins(Arrays.asList("*"));
//	    configuration.setAllowCredentials(true);
//	    configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"));
//	    configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", configuration);
//	    return source;
//	}
	
	@Bean
	InMemoryUserDetailsManager userDetailsManager() {
		ArrayList<UserDetails> details=new ArrayList<UserDetails>();
		for(com.example.vehicle.model.User user:userService.getAllUsers()) {
			details.add(User.withUsername(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).roles("USER").build());
		}
		details.add(User.withUsername("srinu").password(passwordEncoder.encode("43434")).roles("ADMIN").build());
		return new InMemoryUserDetailsManager(details);
	}
	
//	@Bean
//	 public WebMvcConfigurer corsConfigurer() {
//	  return new WebMvcConfigurer() {
//	            public void addCorsMappings(CorsRegistry registry) {
//	                registry.addMapping("/**")
//	                        .allowedOrigins("http://localhost:3000") 
//	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//	                        .allowedHeaders("*")
//	                        .allowCredentials(true);
//	            }
//	        };
//	    }
}


