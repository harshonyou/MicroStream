package com.example.repository;

import com.example.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String userId);

    void addUser(User user);
}
