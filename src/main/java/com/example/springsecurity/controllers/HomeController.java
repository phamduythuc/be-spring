package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/admin")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok(new ResponseDTO<>("TEST ADMIN", 200));
    }

    @GetMapping("/user")
    public ResponseEntity<?> user() {
        return ResponseEntity.ok(new ResponseDTO<>("TEST USER", 200));
    }
}
