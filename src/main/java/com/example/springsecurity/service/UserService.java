package com.example.springsecurity.service;

import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.exception.ExistEmailException;
import com.example.springsecurity.exception.ExistUsernameException;
import com.example.springsecurity.reposiroty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserDTO userDTO) {
        try {
            if (userRepository.findByEmail(userDTO.getEmail()) == null) {
                throw new ExistEmailException();
            }
            if (userRepository.findByUsername(userDTO.getUsername()) == null) {
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
}
