package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.ResponseDTO;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {
    private final JwtUtils jwtUtils;

    @GetMapping("/admin")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok(new ResponseDTO<>("TEST ADMIN", 200));
    }

    @GetMapping("/user")
    public ResponseEntity<?> user() {
        return ResponseEntity.ok(new ResponseDTO<>("TEST USER", 200));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/read")
    public ResponseEntity<?> read() {
        return ResponseEntity.ok(new ResponseDTO<>("CAN READ", 200));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit")
    public ResponseEntity<?> edit() {
        return ResponseEntity.ok(new ResponseDTO<>("CAN EDIT", 200));
    }

    @GetMapping("/getToken")
    @ResponseBody
    public String getToken() {
        User user = new User();
        user.setId(29L);
        String token = jwtUtils.generateToken(user);
        System.out.println("User info : " + jwtUtils.getUserId(token));
        System.out.println("Validate token : " + jwtUtils.validateToken(token));
        return token;
    }
}
