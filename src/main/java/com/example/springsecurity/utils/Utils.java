package com.example.springsecurity.utils;

import org.springframework.util.StringUtils;


public class Utils {
    public static String getToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
