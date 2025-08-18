package com.SpringBoot_LMS.SpringBoot_LMS.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	    @Bean
	    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		 http.csrf((csrf) -> csrf.disable())
		 .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS and set the source
		 .authorizeHttpRequests(requests->requests.requestMatchers("/**").permitAll());
		  return http.build(); 
	 }
	    
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000/")); 
	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
	        configuration.setAllowedHeaders(Arrays.asList("*")); // Allow all headers
	        configuration.setAllowCredentials(true); // If you need to send cookies/auth headers
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration); // Apply to all paths
	        return source;
	    }
}
