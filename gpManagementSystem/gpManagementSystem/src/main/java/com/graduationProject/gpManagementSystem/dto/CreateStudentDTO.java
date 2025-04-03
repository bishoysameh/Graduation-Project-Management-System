package com.graduationProject.gpManagementSystem.dto;

import com.graduationProject.gpManagementSystem.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateStudentDTO {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Level is required")
    private int level ;

    @NotBlank(message = "Student id  is required")
    private String studentId;

    private double gpa;

    // @NotBlank(message = "creditHours is required")
    // @Size(min = 0, max = 160, message = "credit hours must be between 0 and 160 hours")
    private int creditHours;

    @Enumerated(EnumType.STRING)
     private Role role ;

    //  private String status;


    
}
