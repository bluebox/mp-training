package com.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager users() {
		var user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();

		var admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build();

		return new InMemoryUserDetailsManager(user, admin);
	}

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/", "/todos", "/login", "/css/**","/api/getall","/api/todos","/api/todos/id").permitAll()
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//            )
//            .formLogin(form -> form
//                .loginPage("/login")
//                .permitAll()
//            )
//            .logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .permitAll()
//            );
//        return http.build();
//    }

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(customizer -> customizer.disable())
		
				.authorizeHttpRequests(
						(authorize) -> authorize
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/todos/new").authenticated().anyRequest().permitAll())
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

		return http.build();
	}

}

/**
 * package com.example.todo.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.Customizer; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.core.userdetails.User; import
 * org.springframework.security.provisioning.InMemoryUserDetailsManager; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
 * // Import for csrf().disable()
 * 
 * @Configuration public class SecurityConfig {
 * 
 * @Bean public InMemoryUserDetailsManager users() { var user =
 *       User.withDefaultPasswordEncoder() .username("user")
 *       .password("password") .roles("USER") .build(); var admin =
 *       User.withDefaultPasswordEncoder() .username("admin") .password("admin")
 *       .roles("ADMIN") .build(); return new InMemoryUserDetailsManager(user,
 *       admin); }
 * 
 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
 *       Exception { http .csrf(AbstractHttpConfigurer::disable) // Disable CSRF
 *       for API requests if not handled explicitly .authorizeHttpRequests(auth
 *       -> auth .requestMatchers("/", "/todos", "/login", "/css/**",
 *       "/api/getall", "/api/todos", "/api/todos/**").permitAll()
 *       .requestMatchers("/admin/**").hasRole("ADMIN")
 *       .anyRequest().authenticated() ) .formLogin(form -> form
 *       .loginPage("/login") .permitAll() ) .logout(logout -> logout
 *       .logoutUrl("/logout") .logoutSuccessUrl("/") .permitAll() ); return
 *       http.build(); } }
 * 
 */
