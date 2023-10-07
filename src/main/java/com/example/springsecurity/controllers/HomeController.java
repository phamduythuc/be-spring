package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.reposiroty.UserRepository;
import com.example.springsecurity.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    private final UserService userService;

    public HomeController(UserRepository userRepository, RestTemplate restTemplate, UserService userService) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    @GetMapping("/public")
    @ResponseBody
    public String hello() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("https://63c559fdf3a73b3478541366.mockapi.io/persons", HttpMethod.GET, entity, String.class).getBody();
    }

    @GetMapping("/home")
    public ResponseEntity<String> home(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            long sessionExpirationTime = session.getLastAccessedTime() + session.getMaxInactiveInterval() * 1000;
            System.out.println("Session time: " + session.getMaxInactiveInterval());
            long currentTime = System.currentTimeMillis();
            if (sessionExpirationTime < currentTime) {
                System.out.println("Phiên làm việc đã hết hạn");
            } else {
                long l = (sessionExpirationTime - currentTime) / 1000;
                System.out.println("Phiên làm việc còn hạn và thời gian còn lại: " + l);
            }
        } else {
            System.out.println("Phiên làm việc đã hết hạn");
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.auth(userDTO));
    }
}
