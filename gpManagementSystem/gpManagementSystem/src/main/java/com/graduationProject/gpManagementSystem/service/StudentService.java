package com.graduationProject.gpManagementSystem.service;

import com.graduationProject.gpManagementSystem.dto.CreateStudentDTO;
import com.graduationProject.gpManagementSystem.enums.Department;
import com.graduationProject.gpManagementSystem.exception.ResourceNotFoundException;
import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> findByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public List<Student> findByStudentIds(List<String> studentIds) {
        return studentRepository.findByStudentIdIn(studentIds);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }





    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }



    // Update existing student
    public Student updateStudent(Long id, CreateStudentDTO studentDetails) {
        Student student = getStudentById(id);

        student.setUserName(studentDetails.getUsername());
        student.setLevel(studentDetails.getLevel());
        student.setGpa(studentDetails.getGpa());
        student.setDepartment(studentDetails.getDepartment());
        student.setCreditHours(studentDetails.getCreditHours());
        return studentRepository.save(student);
    }

    // Delete student
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }


    
    public List<Student> getStudentsByTeamId(Long teamId) {
        return studentRepository.findByTeamId(teamId);
    }


    public List<Student> getUnassignedStudents() {
        return studentRepository.findByTeamIsNull();
    }



    public List<Student> findUnassignedStudentsInSameDepartment(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
    
        Department department = student.getDepartment();
        return studentRepository.findByTeamIsNullAndDepartment(department);
    }
    
}
