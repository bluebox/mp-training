package dev.kaushik.springJdbc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("dev.kaushik.springJdbc.model")
public class SpringJdbcConfig {

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
        
        dataSource.setUsername("devuser1");
        dataSource.setPassword("devuser1"); 

        return dataSource;
    }
}