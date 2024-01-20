package com.example.service;

import com.example.dto.UserDTO;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Singleton
public class UserServiceImpl  implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(String id, String name) {
        LOGGER.info("Adding user with ID: {}", id);

        if(findById(id).isPresent()) {
            LOGGER.info("User with ID: {} already exists", id);
            return;
        }

        userRepository.addUser(new User(id, name));
    }

    @Override
    public Optional<UserDTO> findById(String id) {
        LOGGER.info("Finding user by ID: {}", id);
        Optional<User> user = userRepository.findById(id);

        return user.map(UserMapper::fromEntity);
    }
}
