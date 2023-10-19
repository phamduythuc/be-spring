package com.example.springsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class);
    }
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
