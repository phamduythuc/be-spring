package com.example.springsecurity.utils;

import com.example.springsecurity.entities.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value(value = "${app.jwtExpiration}")
    private Long jwtExpiration;

    @Value("${app.jwtCookie}")
    private String jwtCookie;

    // tạo thông tin từ user
    public String generateToken(String username) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expireDate)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    //lấy thông tin từ jwt
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    //kiểm tra token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

}
