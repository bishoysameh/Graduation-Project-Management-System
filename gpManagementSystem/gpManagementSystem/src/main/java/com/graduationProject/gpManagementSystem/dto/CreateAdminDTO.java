package com.graduationProject.gpManagementSystem.dto;


import com.graduationProject.gpManagementSystem.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateAdminDTO {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "password cannot be empty")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role ;   
}
