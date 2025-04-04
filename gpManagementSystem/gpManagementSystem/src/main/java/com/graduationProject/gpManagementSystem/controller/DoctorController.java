package com.graduationProject.gpManagementSystem.controller;

import java.util.List;
import java.util.Optional;

// import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.gpManagementSystem.dto.ApiResponse;
import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.service.DoctorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // API to fetch all doctors
    //work
    // @GetMapping
    // public List<Doctor> getAllDoctors() {
    //     return doctorService.getAllDoctors();
    // }




    @GetMapping
public ResponseEntity<List<Doctor>> getAllDoctors(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {


//if you want meta data abput pagination 
    // Pageable pageable = PageRequest.of(page, size);
    // Page<Doctor> doctors = doctorService.getAllDoctors(pageable);
    // return ResponseEntity.ok(doctors);

    //if you want oly content
    Pageable pageable = PageRequest.of(page, size);
    List<Doctor> doctors = doctorService.getAllDoctors(pageable).getContent();
    return ResponseEntity.ok(doctors);
}




//work
    @GetMapping("/{id}")
    public Optional<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    //delete it because it is the same register doctor 
    @PostMapping
    public void addDoctor(@RequestBody Doctor doctor) {
         doctorService.addDoctor(doctor);
    }




    
    //contain error
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Doctor>> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {        
        Doctor updatedDoctor =  doctorService.updateDoctor(id,doctor);

        ApiResponse<Doctor> response = new ApiResponse<>(
        "success", 
        "Doctor updated successfully", 
        updatedDoctor
    );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





    //work
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id ){
          doctorService.deleteDoctor(id);
          
          ApiResponse<Void> successResponse = new ApiResponse<>(
            "success",
            "doctor deleted successfully"
        );
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
    


    
  
}
