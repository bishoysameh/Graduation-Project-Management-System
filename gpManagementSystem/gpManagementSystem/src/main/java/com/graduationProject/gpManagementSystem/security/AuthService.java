package com.graduationProject.gpManagementSystem.security;

import java.util.Optional;

import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


import com.graduationProject.gpManagementSystem.dto.*;
import com.graduationProject.gpManagementSystem.enums.Role;
import com.graduationProject.gpManagementSystem.enums.Status;
import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.model.User;
import com.graduationProject.gpManagementSystem.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor

public class AuthService {
    //banque misr
    // private final CustomerRepository customerRepository;
    // private final AccountRepository accountRepository;
    // private final AuthenticationManager authenticationManager;
    // private final JwtUtils jwtUtils;
    // private final PasswordEncoder passwordEncoder;
    // private final TokenBlacklistService tokenBlacklistService;



    private final PasswordEncoder passwordEncoder; 
    private final UserRepository repository;
    private final JwtUtils jwtService;
    private final AuthenticationManager authenticationManager;







     public LoginResponseDTO registerStudent(CreateStudentDTO request) {
     var user = com.graduationProject.gpManagementSystem.model.Student.builder()
    
     .username(request.getUsername())
     .email(request.getEmail())
     .password(passwordEncoder.encode(request.getPassword()))
     .role(Role.STUDENT)
     .department(request.getDepartment())
     .creditHours(request.getCreditHours())
     .studentId(request.getStudentId())
     .gpa(request.getGpa())
     .level(request.getLevel())
     .status(Status.PENDING)
     .build();
         repository.save(user);
           return LoginResponseDTO.builder().message("Registration pending approval").build();
    }




    public LoginResponseDTO registerDoctor(CreateDoctorDTO request) {
        var user = com.graduationProject.gpManagementSystem.model.Doctor.builder()
       
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.DOCTOR)
        .status(Status.ACCEPTED)
        .specialization(request.getSpecialization())
        .build();
            repository.save(user);
              var jwtToken = jwtService.generateToken(user);
              return LoginResponseDTO.builder().token(jwtToken).build();
          
       }





       public LoginResponseDTO registerAdmin(CreateAdminDTO request) {
        var user = com.graduationProject.gpManagementSystem.model.Admin.builder()
       
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.ADMIN)
        .status(Status.ACCEPTED)
        .build();
            repository.save(user);
              var jwtToken = jwtService.generateToken(user);
              return LoginResponseDTO.builder().token(jwtToken).build();
          
       }






    public LoginResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken (
          request.getEmail(),
          request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    if(user.getStatus().toString().equals("ACCEPTED"))
    {
    var jwtToken = jwtService.generateToken(user);
    return LoginResponseDTO.builder()
    .token(jwtToken)
    .statusCode(HttpStatus.ACCEPTED)
    .tokenType("Bearer")
    .message("login successful")
    .build();
    
    }
    else
    return LoginResponseDTO.builder()
    .statusCode(HttpStatus.FORBIDDEN)
    .message("sorry, you cant login untill admin is accept registeration").build();
    }
















 public void approveUserRegistration(int userId) {
      Optional<User> userOptional = repository.findById(userId);
      if (userOptional.isPresent()) {
          Student user = (Student) userOptional.get();
          user.setStatus(Status.ACCEPTED);
          repository.save(user);
      } else {
          throw new EntityNotFoundException("User not found with ID: " + userId);
      }
  }

  public void rejectUserRegistration(int userId) {
    Optional<User> userOptional = repository.findById(userId);
    if (userOptional.isPresent()) {
        Student user = (Student) userOptional.get();
        user.setStatus(Status.REJECTED);
        repository.save(user);
    } else {
        throw new EntityNotFoundException("User not found with ID: " + userId);
    }
}





//banque misr
    //  @Override
    // public loginResponseDTO login(loginRequestDTO loginRequestDTO) {
    //     Authentication authentication;
    //     // here we did authenticate
    //     try {
    //         authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken((loginRequestDTO.getEmail()), loginRequestDTO.getPassword()));
    //     }
    //     catch (AuthenticationException e){
    //         return loginResponseDTO.builder().statusCode(HttpStatus.UNAUTHORIZED).message(e.getMessage()).build();
    //     }
    //     //we authenticate and put the context across spring boot to access it from anywhere
    //     SecurityContextHolder.getContext().setAuthentication(authentication);

    //     //here we generate token
    //     String jwt = jwtUtils.generateToken(authentication);

    //     return loginResponseDTO.builder()
    //             .token(jwt)
    //             .statusCode(HttpStatus.ACCEPTED)
    //             .tokenType("Bearer")
    //             .message("login successful")
    //             .build();

    // }


    //  @Override
    // public ResponseEntity<Void> logout(String token) {
    //     String jwtToken = token.replace("Bearer ", "");
    //      tokenBlacklistService.blackListToken(jwtToken);
    //      return ResponseEntity.ok().build();
    // }


}
