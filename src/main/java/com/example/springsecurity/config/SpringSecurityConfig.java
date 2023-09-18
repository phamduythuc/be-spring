package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("user1").password(passwordEncoder().encode("user1")).roles("USER").build();
        UserDetails user2 = User.builder().username("user2").password(passwordEncoder().encode("user2")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
