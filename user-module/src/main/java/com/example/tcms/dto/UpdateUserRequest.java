package com.example.tcms.dto;

import com.example.tcms.enums.Role;
import lombok.Data;

@Data
public class UpdateUserRequest {
    String firstName;

    String lastName;

    Role role;

    Boolean active;
}
