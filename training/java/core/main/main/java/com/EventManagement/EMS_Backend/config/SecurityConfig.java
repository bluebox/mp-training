package com.EventManagement.EMS_Backend.config;


import java.util.Arrays;
import java.util.Collections;
import io.jsonwebtoken.JwtParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain secfilterchain(HttpSecurity http) throws Exception {
    	 http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authorizeRequests(
                 authorize -> authorize
                 .requestMatchers("/Admin/viewevents").hasAnyRole("Admin","Faculty","Student")
                 .requestMatchers("/Admin/**","/api/report/**").hasRole("Admin")
                 .requestMatchers("/api/Registration/viewregstudents").hasRole("Faculty")
                 .requestMatchers("/api/**").authenticated()
                 .anyRequest().permitAll())
         .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
         .csrf(csrf -> csrf.disable())
         .cors(cors -> cors.configurationSource(corsConfigurationSource()));
         //.httpBasic(Customizer.withDefaults())
         //.formLogin(Customizer.withDefaults());
 return http.build();
}

private CorsConfigurationSource corsConfigurationSource() {
 return new CorsConfigurationSource() {
     @Override
     public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
         CorsConfiguration ccfg = new CorsConfiguration();
         ccfg.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
         ccfg.setAllowedMethods(Collections.singletonList("*"));
         ccfg.setAllowCredentials(true);
         ccfg.setAllowedHeaders(Collections.singletonList("*"));
         ccfg.setExposedHeaders(Arrays.asList("Authorization"));
         ccfg.setMaxAge(3600L);
         return ccfg;

     }

	 
 };

}


@Bean
PasswordEncoder passwordEncoder() {
 return new BCryptPasswordEncoder();
}




}