package com.example.imgur.service;

import com.example.imgur.model.User;
import com.example.imgur.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ImgurService imgurService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ImgurService imgurService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.imgurService = imgurService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public String uploadImageForUser(String username, byte[] imageBytes) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            String imageUrl = imgurService.uploadImage(imageBytes);
            return imageUrl;
        }
        return "User not found";
    }

    public String deleteImageForUser(String username, String imageHash) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return imgurService.deleteImage(imageHash);
        }
        return "User not found";
    }

    public User getUserDetails(String username) {
        return userRepository.findByUsername(username);
    }
}
