package com.graduationProject.gpManagementSystem.dto;

import com.graduationProject.gpManagementSystem.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CreateAdminDTO {

   private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role ;   
}
