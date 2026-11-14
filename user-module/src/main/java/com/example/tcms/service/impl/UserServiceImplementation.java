package com.example.tcms.service.impl;

import com.example.tcms.dto.CreateUserRequest;
import com.example.tcms.dto.UpdateUserRequest;
import com.example.tcms.dto.UserResponse;
import com.example.tcms.exceptions.DuplicateException;
import com.example.tcms.exceptions.NotFoundException;
import com.example.tcms.mapper.impl.UserMapperImpl;
import com.example.tcms.repository.UserRepository;
import com.example.tcms.service.UserService;
import com.example.tcms.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImplementation.class);
    private UserRepository userRepository;
    private UserMapperImpl userMapper;

    public UserServiceImplementation(UserRepository userRepository, UserMapperImpl userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse create(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail()) || userRepository.existsByUsername(request.getUsername())) {
            log.info("User already exists {}", request.getEmail());
            throw new DuplicateException("User already exists");
        }
        User user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .active(true)
                .isDeleted(false)
                .build();
        userRepository.save(user);
        log.info("User has been saved: {}", user.getEmail());

        return userMapper.toResponse(user);

    }

    @Override
    public UserResponse getById(String id) {
        Optional<User> user = userRepository.findByIdAndIsDeletedFalse(id);
        if (!user.isPresent()) {
            log.info("User not found with id: {}", id);
            throw new NotFoundException("User is not found");
        }
        log.info("User found: {}", user);
        return userMapper.toResponse(user.get());
    }

    @Override
    public List<UserResponse> getAll() {
        log.info("before finding the user");
        List<User> users = userRepository.findByIsDeletedFalse();
        log.info("Users list has been fetched successfully");
        return users.stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse update(String id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        if (request.getActive() != null) {
            user.setActive(request.getActive());
        }

        User updatedUser = userRepository.save(user);
        log.info("User has been update successfully: {}",id);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void delete(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            log.info("User with id --> {} is not available",id);
            throw new NotFoundException("User not found");
        }
        log.info("Before setting the delete");
        user.get().setDeleted(true);
        log.info("After setting the delete");
        userRepository.save(user.get());
        log.info("User has been deleted successfully: {}",id);
    }


}
