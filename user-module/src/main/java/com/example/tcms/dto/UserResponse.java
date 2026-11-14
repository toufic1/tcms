package com.example.tcms.dto;

import com.example.tcms.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    String id;

    String username;

    String firstName;

    String lastName;

    String email;

    Role role;

    boolean active;

    LocalDateTime createdAt;


}
