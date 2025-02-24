package com.graduationProject.gpManagementSystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.graduationProject.gpManagementSystem.model.Team;
import com.graduationProject.gpManagementSystem.repository.TeamRepository;

@Service
public class TeamService {

 private final TeamRepository teamRepository;

 public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
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
        return teamRepository.findById(id).map(team -> {
           team.setName(teamDetails.getName());
           return teamRepository.save(team);
        }).orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public void deleteTeam(Long id ){
         teamRepository.deleteById(id);
    }


    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }



 }
