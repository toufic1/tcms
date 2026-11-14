package com.example.tcms.mapper;

import com.example.tcms.dto.CreateUserRequest;
import com.example.tcms.dto.UpdateUserRequest;
import com.example.tcms.dto.UserResponse;
import com.example.tcms.user.User;

public interface UserMapper {
    User toEntity(CreateUserRequest request);

    UserResponse toResponse(User user);

    void update(UpdateUserRequest request, User user);
}
