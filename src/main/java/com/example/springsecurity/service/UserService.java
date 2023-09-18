package com.example.springsecurity.service;

import com.example.springsecurity.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Bean
    public User getUserDetail(){
        return new User("user123","123");
    }
    @Bean
    public String getName(){
        return "khue1234";
    }

}
