package com.example.demoWeb.controller;

import com.example.demoWeb.model.User;
import com.example.demoWeb.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
    @GetMapping("/phone")
    public Optional<User> getUserByPhoneNumber(@RequestParam String phoneNumber){
       return userService.getUserByPhoneNumber(phoneNumber);
    }
}
