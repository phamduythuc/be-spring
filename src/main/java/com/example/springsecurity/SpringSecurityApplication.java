package com.example.springsecurity;

import com.example.springsecurity.entities.Address;
import com.example.springsecurity.reposiroty.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityApplication  implements CommandLineRunner {

    private final AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class);
    }
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @Override
    public void run(String... args) throws Exception {
        List<Address> addresses=addressRepository.findAll();
        System.out.println(addresses);
    }
}
