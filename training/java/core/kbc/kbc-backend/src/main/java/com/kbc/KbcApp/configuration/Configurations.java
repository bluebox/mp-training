package com.kbc.KbcApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Configurations{

	@Bean
	public DriverManagerDataSource myDataSource() {
		DriverManagerDataSource myDS=new DriverManagerDataSource();
		myDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		myDS.setUrl("jdbc:mysql://localhost:3306/kbc_db");
		myDS.setUsername("root");
		myDS.setPassword("root");
		return myDS;
		
	}
	
	@Bean
	public JdbcTemplate myTemplate() {
		JdbcTemplate template=new JdbcTemplate();
		template.setDataSource(myDataSource());
		return template;
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		NamedParameterJdbcTemplate template=new NamedParameterJdbcTemplate(myDataSource());
		return template;
	}
}