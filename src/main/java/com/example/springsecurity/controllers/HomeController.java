package com.example.springsecurity.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class HomeController {
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

    @GetMapping("/test")
    public void test(HttpServletRequest request) {
        System.out.println(request.getHeader("myNumber"));
    }
}
