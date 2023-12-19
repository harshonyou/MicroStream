package com.example.service;

import com.example.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> getUser(String id);

    void addUser(String id, String name);
}
