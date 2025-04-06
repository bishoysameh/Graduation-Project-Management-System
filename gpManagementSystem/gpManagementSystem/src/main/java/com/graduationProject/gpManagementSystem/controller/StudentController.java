package com.graduationProject.gpManagementSystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.gpManagementSystem.dto.ApiResponse;
import com.graduationProject.gpManagementSystem.dto.CreateStudentDTO;
// import com.graduationProject.gpManagementSystem.dto.ApiResponse;
import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

// ✅ Get all students
@GetMapping
public List<Student> getAllStudents() {
    return studentService.getAllStudents();
    // List<Student> students = studentService.getAllStudents();
    // ApiResponse<List<Student>> response = new ApiResponse<>(
    //     "success", "All students retrieved successfully", students
    // );
    // return ResponseEntity.ok(response);
}




    // ✅ Get a student by ID
    @GetMapping("/{id}")

    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
        // Student student = studentService.getStudentById(id);
        // ApiResponse<Student> response = new ApiResponse<>(
        //     "success", "Student retrieved successfully", student
        // );
        // return ResponseEntity.ok(response);
    }






    // ✅ Update an existing student
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(
            @PathVariable Long id, @RequestBody CreateStudentDTO studentDetails) {
        Student updated = studentService.updateStudent(id, studentDetails);
        ApiResponse<Student> response = new ApiResponse<>(
            "success", "Student updated successfully", updated
        );
        return ResponseEntity.ok(response);
    }




    // ✅ Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        ApiResponse<Void> response = new ApiResponse<>(
            "success", "Student deleted successfully"
        );
        return ResponseEntity.ok(response);
    }






    @GetMapping("/team/{teamId}")
    public List<Student> getStudentsByTeamId(@PathVariable Long teamId) {
        return studentService.getStudentsByTeamId(teamId);
        // List<Student> students = studentService.getStudentsByTeamId(teamId);

        // ApiResponse<List<Student>> response = new ApiResponse<>(
        //     "success",
        //     "Students retrieved successfully",
        //     students
        // );

        // return ResponseEntity.ok(response);
    }



// get students without teams in all departments to help admin know students still without team
    @GetMapping("/unassigned")
public ResponseEntity<List<Student>> getUnassignedStudents() {
    List<Student> unassignedStudents = studentService.getUnassignedStudents();
    return ResponseEntity.ok(unassignedStudents);
}



// this api for students to help find team with students at same department (can chat with them to create team)
@GetMapping("/unassigned/department/{studentId}")
public ResponseEntity<List<Student>> getUnassignedStudentsInSameDepartment(@PathVariable Long studentId) {
    List<Student> students = studentService.findUnassignedStudentsInSameDepartment(studentId);
    return ResponseEntity.ok(students);
}






}