package com.example.tcms.user;

import com.example.tcms.common.Common;
import com.example.tcms.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "users")
public class User extends Common {
    String username;

    String firstName;

    String lastName;

    String email;

    String password;

    Role role;

    boolean active;
    boolean isDeleted;

    String profileImage;
    LocalDateTime lastLogin;

    public User(String username, String firstName, String lastName, String email, String password, Role role, boolean active) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
        this.lastLogin = LocalDateTime.now();
    }
}

