package com.example.springsecurity.service;

import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.exception.ExistEmailException;
import com.example.springsecurity.exception.ExistUsernameException;
import com.example.springsecurity.reposiroty.RoleRepository;
import com.example.springsecurity.reposiroty.UserRepository;
import com.example.springsecurity.utils.ERole;
import lombok.RequiredArgsConstructor;
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
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public User saveUser(UserDTO userDTO) {
        try {
            if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
                throw new ExistEmailException();
            }
            if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
                throw new ExistUsernameException();
            }
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setEmail(userDTO.getEmail());
            Role role = roleRepository.findByRoleName(ERole.ROLE_USER).orElseThrow(() -> new Exception());
            user.setRoles(Set.of(role));
            return userRepository.save(user);
        } catch (ExistEmailException e) {
            System.out.println(e.getMessage());
        } catch (ExistUsernameException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void auth(UserDTO userDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login: "+SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
