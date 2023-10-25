package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.ResponseDTO;
import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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
        userService.auth(userDTO);
        return ResponseEntity.ok(new ResponseDTO<>("Login success", 200));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(new ResponseDTO<>("Test OK", 200));
    }
}