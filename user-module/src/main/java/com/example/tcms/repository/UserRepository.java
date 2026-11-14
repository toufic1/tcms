package com.example.tcms.repository;

import com.example.tcms.enums.Role;
import com.example.tcms.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<User> findByRole(Role role);

    List<User> findByIsDeletedFalse();
    Optional<User> findByIdAndIsDeletedFalse(String id);
}