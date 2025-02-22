package com.graduationProject.gpManagementSystem.dto;

import com.graduationProject.gpManagementSystem.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CreateStudentDTO {

    private String username;
    private String email;
    private String password;
    private String department;
    private int year;

    @Enumerated(EnumType.STRING)
     private Role role ;

    //  private String status;


    
}
