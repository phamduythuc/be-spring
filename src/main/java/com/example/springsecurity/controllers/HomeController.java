package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.reposiroty.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class HomeController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public HomeController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/home")
    public ResponseEntity<String> home(HttpServletRequest request) {
        HttpSession session= request.getSession();
        if (session!=null){
            long sessionExpirationTime=session.getLastAccessedTime()+session.getMaxInactiveInterval()*1000;
            System.out.println("Session time: "+session.getMaxInactiveInterval());
            long currentTime = System.currentTimeMillis();
            if (sessionExpirationTime<currentTime){
                System.out.println("Phiên làm việc đã hết hạn");
            }
            else {
                long l = (sessionExpirationTime - currentTime) / 1000;
                System.out.println("Phiên làm việc còn hạn và thời gian còn lại: "+l);
            }
        }
        else {
            System.out.println("Phiên làm việc đã hết hạn");
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        User user=userRepository.findByUsername(userDTO.getUsername());
        if (user==null){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(userRepository.saveAndFlush(new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()))));
        }
    }
}
