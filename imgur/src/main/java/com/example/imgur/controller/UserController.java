package com.example.imgur.controller;

import com.example.imgur.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(userService.registerUser(username, password));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(userService.authenticateUser(username, password));
    }

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam String username, @RequestParam byte[] imageBytes) {
        String result = userService.uploadImageForUser(username, imageBytes);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete-image/{imageHash}")
    public ResponseEntity<?> deleteImage(@RequestParam String username, @PathVariable String imageHash) {
        String result = userService.deleteImageForUser(username, imageHash);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserDetails(username));
    }
}
