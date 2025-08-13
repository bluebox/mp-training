package dev.kaushik.springJdbc.config;

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
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
                    .requestMatchers("/api/**").permitAll()      
                    .requestMatchers("/courses/**").authenticated() 
                    .anyRequest().denyAll()
            )
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form
                    .defaultSuccessUrl("/courses", true)
            )
            .logout(Customizer.withDefaults());

        return http.build();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails kaushik = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(kaushik, admin);
    }
}
