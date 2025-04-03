package com.graduationProject.gpManagementSystem.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.dto.TeamRequest;
import com.graduationProject.gpManagementSystem.enums.Status;
import com.graduationProject.gpManagementSystem.exception.InvalidStudentCountException;
import com.graduationProject.gpManagementSystem.exception.InvalidTeamSizeException;
import com.graduationProject.gpManagementSystem.exception.ResourceNotFoundException;
import com.graduationProject.gpManagementSystem.exception.UserAlreadyExistException;
import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.model.Team;
import com.graduationProject.gpManagementSystem.repository.TeamRepository;

@Service
public class TeamService {

private final StudentService studentService;
 private final TeamRepository teamRepository;

 public TeamService(TeamRepository teamRepository , StudentService studentService) {
    this.teamRepository = teamRepository;
    this.studentService = studentService;
}




    public List<Team> getAllTeams() {
        return teamRepository.findAll();  
    }

    public Optional<Team> getTeamById(Long id){
           return teamRepository.findById(id);
    }

    public void addTeam (Team team){
         teamRepository.save(team);
    }





     // Update an existing doctor
     public Team updateTeam(Long id, Team teamDetails) {

           Team updatedTeam = teamRepository.findById(id).map(team -> {
           team.setName(teamDetails.getName());
           return teamRepository.save(team);
           }).orElseThrow(() -> new ResourceNotFoundException("Team not found")); 
          return updatedTeam;
    }




    // public void deleteTeam(Long id ){
    //      teamRepository.deleteById(id);
    // }



    public void deleteTeam(Long id) {
                 teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
        teamRepository.deleteById(id);
    }
    







    public Team createTeam(String studentId , TeamRequest request) {
        Optional<Student> requesterOpt = studentService.findByStudentId(studentId);

        if (requesterOpt.isEmpty()) {
            throw new ResourceNotFoundException("Requesting student not found");        }

        Student requester = requesterOpt.get();

        if (requester.getTeam() != null) {
            throw new UserAlreadyExistException("You are already in a team and cannot create another one.");
        }

        if (request.getStudentIds().size() != 6) {
            throw new InvalidTeamSizeException("A team must have exactly 6 members");
        }   

         List<Student> students = studentService.findByStudentIds(request.getStudentIds());

        if (students.size() != 6) {
            throw new InvalidStudentCountException("Some student IDs are invalid.");
        }


        if (students.stream().anyMatch(student-> student.getTeam() != null)){
            throw new UserAlreadyExistException("One or more students are already in a team.");
        }


        /////////////////////////
        if (students.stream().anyMatch(student-> student.getStatus() != Status.ACCEPTED)){
            throw new UserAlreadyExistException("One or more students still pinding");
        }



        //////////////////////////////

           students.get(0).setTeamLeader(true);
        
        Team newTeam = new Team();
        newTeam.setName(request.getName());
        newTeam.setStudents(students);    
        teamRepository.save(newTeam);


        for (Student student : students) {
            student.setTeam(newTeam);
            studentService.saveStudent(student);
        }

         teamRepository.save(newTeam);
         return newTeam;
    }
 }
