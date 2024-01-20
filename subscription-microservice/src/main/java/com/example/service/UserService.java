package com.example.service;

import com.example.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    void addUser(String id, String name);
    Optional<UserDTO> findById(String id);
}
