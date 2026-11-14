package com.example.tcms.user;

import com.example.tcms.common.Common;
import com.example.tcms.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
// "user" is a reserved word in PostgreSQL, so the table is called "users"
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Common {

    // unique = the database itself refuses duplicates, even if a service check is forgotten
    @Column(nullable = false, unique = true)
    private String username;

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Store "ADMIN", not 0. With ORDINAL, adding/reordering enum values would corrupt existing rows.
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean active;

    private boolean isDeleted;

    private String profileImage;

    private LocalDateTime lastLogin;

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
