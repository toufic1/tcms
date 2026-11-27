package com.example.tcms.dto;

import com.example.tcms.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @Size(min = 1, max = 255)
    String firstName;

    @Size(max = 255)
    String lastName;
    Role role;

    Boolean active;
}
