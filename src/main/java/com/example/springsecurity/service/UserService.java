package com.example.springsecurity.service;

import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.exception.ExistEmailException;
import com.example.springsecurity.exception.ExistUsernameException;
import com.example.springsecurity.reposiroty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserDTO userDTO) {
        try {
            if (userRepository.findByEmail(userDTO.getEmail()) != null) {
                throw new ExistEmailException();
            }
            if (userRepository.findByUsername(userDTO.getUsername()) != null) {
                throw new ExistUsernameException();
            }
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setEmail(userDTO.getEmail());
            return userRepository.save(user);
        } catch (ExistEmailException e) {
            System.out.println(e.getMessage());
        } catch (ExistUsernameException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }

    public String auth(UserDTO userDTO) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDTO.getUsername(),userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "login successful!";
    }
}
