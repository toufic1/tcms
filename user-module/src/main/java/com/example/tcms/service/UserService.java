package com.example.tcms.service;

import com.example.tcms.dto.CreateUserRequest;
import com.example.tcms.dto.UpdateUserRequest;
import com.example.tcms.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(CreateUserRequest request);

    UserResponse getById(String id);

    List<UserResponse> getAll();

    UserResponse update(String id,
                        UpdateUserRequest request);

    void delete(String id);
}
