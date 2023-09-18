package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test1");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
