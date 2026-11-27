package com.example.tcms.controller;


import com.example.tcms.dto.CreateUserRequest;
import com.example.tcms.dto.UpdateUserRequest;
import com.example.tcms.dto.UserResponse;
import com.example.tcms.response.ApiResponse;
import com.example.tcms.service.impl.UserServiceImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserServiceImplementation userServiceImplementation;

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse response = userServiceImplementation.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "User created successfully"));
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userServiceImplementation.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userServiceImplementation.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") String id, @Valid@RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userServiceImplementation.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        System.out.println("Before");
        userServiceImplementation.delete(id);
        System.out.println("After");
        return ResponseEntity.noContent().build();
    }
}
