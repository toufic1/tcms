package com.example.tcms.dto;

import com.example.tcms.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank@NotEmpty
    String username;
    @NotBlank@NotEmpty
    String firstName;

    String lastName;
    @Email@NotBlank
    String email;
    @NotBlank@Size(min = 3,max = 12,message = "Password should be between 3 and 12")
    String password;
    @NotBlank@NotEmpty
    Role role;
}
