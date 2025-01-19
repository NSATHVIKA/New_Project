package com.example.imgur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.imgur.model.Users;
import com.example.imgur.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String register(Users user) {
        if (userRepository.existsById(user.getUsername())) {
            return "User already exists.";
        }
        userRepository.save(user);
        return "User registered successfully.";
    }

    public String login(String username, String password) {
        Users user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "Login successful.";
        }
        return "Invalid credentials.";
    }

    public boolean authenticate(String username) {
        return userRepository.existsById(username);
    }

    public Users getUserInfo(String username) {
        return userRepository.findByUsername(username);
    }
}