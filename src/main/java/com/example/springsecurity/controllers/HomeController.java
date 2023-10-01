package com.example.springsecurity.controllers;

import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.reposiroty.UserRepository;
import com.example.springsecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final UserRepository userRepository;

    private final UserService userService;

    public HomeController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
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
       return ResponseEntity.ok(userService.saveUser(userDTO));
    }
    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.auth(userDTO));
    }
}
