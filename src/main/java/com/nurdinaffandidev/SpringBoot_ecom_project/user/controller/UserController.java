package com.nurdinaffandidev.SpringBoot_ecom_project.user.controller;

import com.nurdinaffandidev.SpringBoot_ecom_project.user.model.User;
import com.nurdinaffandidev.SpringBoot_ecom_project.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User newUser) {
        User addedUser = userService.registerUser(newUser);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
    }
}
