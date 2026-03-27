package com.example.demoWeb.service;

import com.example.demoWeb.model.User;
import com.example.demoWeb.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    public User createUser(User user) {
        log.info("Creating user with email: {}", user.getEmail());
        return userRepository.save(user);
    }
    public User getUserByEmail(String email) {
        log.info("Getting a user by email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User not found with email: {}", email);
                    return new RuntimeException("User not found");
                });
    }
}
