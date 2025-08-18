package com.lms.LMS_Springboot.Config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter; 

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain secfilterchain(HttpSecurity http) throws Exception {
        http
            //.csrf(csrf -> csrf.disable())
        .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            .cors(Customizer.withDefaults()) 
            .authorizeHttpRequests(req -> req
                //.requestMatchers("/", "/Book/**").permitAll()
                //.requestMatchers("/", "/Member/**","/issuerecords/**","/Reports/**").permitAll()
            		.requestMatchers("/Login/csrftoken").permitAll()
            	
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .disable()
                )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    
    
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
 
    

}
