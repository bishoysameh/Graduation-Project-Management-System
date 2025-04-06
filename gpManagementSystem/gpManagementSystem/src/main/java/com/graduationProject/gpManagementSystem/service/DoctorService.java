package com.graduationProject.gpManagementSystem.service;

import java.util.List;
// import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.dto.CreateDoctorDTO;
import com.graduationProject.gpManagementSystem.enums.Department;
import com.graduationProject.gpManagementSystem.exception.ResourceNotFoundException;
import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.repository.DoctorRepository;
import com.graduationProject.gpManagementSystem.repository.StudentRepository;

@Service
public class DoctorService {

 private final DoctorRepository doctorRepository;

 private final StudentRepository studentRepository;

 public DoctorService(DoctorRepository doctorRepository , StudentRepository studentRepository) {
    this.doctorRepository = doctorRepository;
    this.studentRepository = studentRepository;
}

    // public List<Doctor> getAllDoctors() {
    //     return doctorRepository.findAll();  
    // }

    public Page<Doctor> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAll(pageable);  
    }

   



    public Optional<Doctor> getDoctorById(Long id){
           return doctorRepository.findById(id);
    }


    //remove it because there is registerDoctor methon in authentication not need to this.
    public void addDoctor (Doctor doctor){
         doctorRepository.save(doctor);
    }

     // Update an existing doctor
    //  public Doctor updateDoctor(Long id, Doctor doctorDetails) {
    //     return doctorRepository.findById(id).map(doctor -> {
    //         doctor.setUserName(doctorDetails.getUsername());
    //         doctor.setSpecialization(doctorDetails.getSpecialization());
    //         doctor.setEmail(doctorDetails.getEmail());
    //         return doctorRepository.save(doctor);
    //     }).orElseThrow(() -> new RuntimeException("Doctor not found"));
    // }

public Doctor updateDoctor(Long id, CreateDoctorDTO doctorDetails) {
    Doctor updatedDoctor = doctorRepository.findById(id).map(doctor -> {
        doctor.setUserName(doctorDetails.getUsername());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setDepartment(doctorDetails.getDepartment());
        return doctorRepository.save(doctor);
    }).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

    return updatedDoctor;
   
}




    // public void deleteDoctor(Long id ){
    //      doctorRepository.deleteById(id);
    // }
    
    public void deleteDoctor(Long id ){
         doctorRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        doctorRepository.deleteById(id);
        }







    public List<Doctor> findDoctorsByDepartmentFromStudent(Long studentId) {
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

    Department department = student.getDepartment();

    return doctorRepository.findByDepartment(department);
}

 }
