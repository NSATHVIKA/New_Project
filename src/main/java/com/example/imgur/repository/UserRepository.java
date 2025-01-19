package com.example.imgur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.imgur.model.Users;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username);
}
