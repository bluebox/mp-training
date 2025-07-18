package com.example.vehicle.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan("com.example.vehicle")
@Slf4j
public class JdbcSecurityConfig {
	
	@Value("${driver-class-name}")
 	private String driverClassName;
	
	@Value("${url}")
	private String url;
	
	@Value("${config.username}")
	private String username;
	
	@Value("${config.password}")
	private String password;
	
	 @Bean
	    public DataSource mysqlDataSource() {	
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	        log.info(driverClassName+" "+url+" "+username+" "+" "+password);
	        dataSource.setDriverClassName(driverClassName);
	        dataSource.setUrl(url);
	        dataSource.setUsername(username);
	        dataSource.setPassword(password); 

	        return dataSource;

}
}
