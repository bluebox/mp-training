package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/", "/public", "/error", "/css/**").permitAll()
                                .requestMatchers("/admin").denyAll()
                                .requestMatchers("/user", "/submit", "/submit-form", "/cause-error").authenticated()
                                .anyRequest().denyAll()
                )
                .formLogin(login -> login.defaultSuccessUrl("/", true));
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        PasswordEncoder encoder = passwordEncoder();
        UserDetails user = User.withUsername("user1")
            .password(encoder.encode("passwords"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}