package com.example.service;

import com.example.dto.UserDTO;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class UserServiceImpl  implements UserService {
    @Inject
    private UserRepository userRepository;

    @Override
    public void addUser(String id, String name) {
        if(findById(id).isPresent())
            return;

        userRepository.addUser(new User(id, name));
    }

    @Override
    public Optional<UserDTO> findById(String id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(UserMapper::fromEntity);
    }
}
