package com.example.tcms.dto;

import com.example.tcms.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank
    @Size(max = 255)
    String username;
    @NotBlank
    @Size(max = 255)
    String firstName;
    @Size(max = 255)
    String lastName;
    @Email
    @NotBlank
    @Size(max = 255)
    String email;
    @NotBlank
    @Size(min = 3, max = 12, message = "Password should be between 3 and 12")
    String password;
    @NotNull
    Role role;
}
