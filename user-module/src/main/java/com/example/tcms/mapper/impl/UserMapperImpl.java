package com.example.tcms.mapper.impl;

import com.example.tcms.dto.CreateUserRequest;
import com.example.tcms.dto.UpdateUserRequest;
import com.example.tcms.dto.UserResponse;
import com.example.tcms.mapper.UserMapper;
import com.example.tcms.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(CreateUserRequest request) {
        return null;
    }

    @Override
    public UserResponse toResponse(User user) {
            return UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .active(user.isActive())
                    .createdAt(user.getCreatedAt()
                    .build();
    }

    @Override
    public void update(UpdateUserRequest request, User user) {

    }
}
