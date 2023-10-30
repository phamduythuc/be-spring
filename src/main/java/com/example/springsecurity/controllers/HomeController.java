package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

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

    @GetMapping("/public")
    public ResponseEntity<?> publicApi() {
        return ResponseEntity.ok(new ResponseDTO<>("Hello Khue1234 ", 200));
    }

}
