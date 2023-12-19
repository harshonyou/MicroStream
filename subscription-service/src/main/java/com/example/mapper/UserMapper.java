package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.model.User;

public class UserMapper {
    public static User fromDTO(UserDTO userDTO) {
        User u = new User();
        u.setId(userDTO.getId());
        u.setName(userDTO.getName());
        return u;
    }

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }
}
