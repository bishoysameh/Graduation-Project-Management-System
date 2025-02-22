package com.graduationProject.gpManagementSystem.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.gpManagementSystem.dto.CreateAdminDTO;
import com.graduationProject.gpManagementSystem.dto.CreateDoctorDTO;
import com.graduationProject.gpManagementSystem.dto.CreateStudentDTO;
import com.graduationProject.gpManagementSystem.dto.LoginRequestDTO;
import com.graduationProject.gpManagementSystem.dto.LoginResponseDTO;
import com.graduationProject.gpManagementSystem.security.AuthService;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

     private final AuthService service;


    @PostMapping("/registerStudent")
    public ResponseEntity<LoginResponseDTO> registerStudent(
            @RequestBody CreateStudentDTO request
    ){
      return ResponseEntity.ok(service.registerStudent(request));
         }





    @PostMapping("/registerDoctor")
    public ResponseEntity<LoginResponseDTO> registerDoctor(
            @RequestBody CreateDoctorDTO request
    ){
    return ResponseEntity.ok(service.registerDoctor(request));
        }



    @PostMapping("/registerAdmin")
    public ResponseEntity<LoginResponseDTO> registerAdmin(
            @RequestBody CreateAdminDTO request
    ){
    return ResponseEntity.ok(service.registerAdmin(request));
        }



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request
    ){
        return ResponseEntity.ok(service.login(request));

    }



    @PutMapping("/{userId}/approve")
    @PreAuthorize("hasAuthority('ADMIN')")

    public ResponseEntity<Void> approveUserRegistration(@PathVariable int userId) {
        service.approveUserRegistration(userId);
        return ResponseEntity.ok().build();
    }




    @PutMapping("/{userId}/reject")
    @PreAuthorize("hasAuthority('ADMIN')")

    public ResponseEntity<Void> rejectUserRegistration(@PathVariable int userId) {
        service.rejectUserRegistration(userId);
        return ResponseEntity.ok().build();
    }

    
}
