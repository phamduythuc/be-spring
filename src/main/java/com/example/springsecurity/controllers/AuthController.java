package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.ResponseDTO;
import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.ok(new ResponseDTO<>("Register success", 200));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {

        return ResponseEntity.ok(new ResponseDTO<>(userService.auth(userDTO), 200));
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<?> refreshToken() {
        return ResponseEntity.ok(new ResponseDTO<>("Test OK", 200));
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> userInfo(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(new ResponseDTO<>("Success", 200, userService.userInfo(token)));
    }
}
