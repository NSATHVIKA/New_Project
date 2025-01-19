package com.example.imgur.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.imgur.model.Users;
import com.example.imgur.service.ImgurService;
import com.example.imgur.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImgurService imgurService;

    @PostMapping("/auth/register")
    public String registerUser(@RequestBody Users user) {
        return userService.register(user);
    }

    @PostMapping("/auth/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @PostMapping("/images/upload")
    public String uploadImage(@RequestParam String username, @RequestParam byte[] imageData) {
        if (userService.authenticate(username)) {
            return imgurService.uploadImage(imageData);
        }
        return "Unauthorized";
    }

    @DeleteMapping("/images/delete")
    public String deleteImage(@RequestParam String username, @RequestParam String imageHash) {
        if (userService.authenticate(username)) {
            return imgurService.deleteImage(imageHash);
        }
        return "Unauthorized";
    }

    @GetMapping("/user/{username}")
    public Users getUserInfo(@PathVariable String username) {
        return userService.getUserInfo(username);
    }
}
