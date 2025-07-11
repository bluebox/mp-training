package com.product.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@ComponentScan(basePackages = "com.product")
@PropertySource("classpath:product-service.properties")
public class ProductConfiguration {
	
	 @Value("${product.datasource.url}")
	    private String url;

	    @Value("${product.datasource.username}")
	    private String username;

	    @Value("${product.datasource.password}")
	    private String password;

	    @Value("${product.datasource.driver-class-name}")
	    private String driverClassName;
	
	@Bean
    public DataSource productDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }
	
	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplate(DataSource dataSournce) {
		return new NamedParameterJdbcTemplate(dataSournce);
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .anyRequest().permitAll()
            .and()
            .httpBasic().disable()
            .formLogin().disable();

        return http.build();
    }
}
