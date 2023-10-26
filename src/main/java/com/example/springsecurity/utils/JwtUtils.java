package com.example.springsecurity.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpiration}")
    private Long jwtExpiration;

    @Value("${app.jwtCookie}")
    private String jwtCookie;


}
