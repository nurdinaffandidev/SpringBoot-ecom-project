package com.nurdinaffandidev.SpringBoot_ecom_project.user.service;

import com.nurdinaffandidev.SpringBoot_ecom_project.user.model.User;
import com.nurdinaffandidev.SpringBoot_ecom_project.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // for postman testing
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(@Valid User newUser) {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public String verify(User user) {
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        return "Login Failed";
    }
}
