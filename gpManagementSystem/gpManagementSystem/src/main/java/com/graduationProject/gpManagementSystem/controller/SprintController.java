package com.graduationProject.gpManagementSystem.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.gpManagementSystem.dto.ApiResponse;
import com.graduationProject.gpManagementSystem.dto.SprintRequest;
import com.graduationProject.gpManagementSystem.model.Sprint;
import com.graduationProject.gpManagementSystem.service.SprintService;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    // ✅ Create Sprint (Tasks are selected from project tasks)
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Sprint>> createSprint(@RequestBody SprintRequest request) {
        Sprint createdSprint =  sprintService.createSprint(request);
         ApiResponse<Sprint> response = new ApiResponse<>(
        "success",
        "Sprint created successfully",
        createdSprint
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(response);   
    }



    

    // ✅ Get All Sprints for a Project
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Sprint>> getSprintsByProject(@PathVariable Long projectId) {
        List<Sprint> sprints = sprintService.getSprintsByProject(projectId);
        return ResponseEntity.ok(sprints);
    }





     // Endpoint to get the Burndown Chart data for a specific sprint
    //  @GetMapping("/{sprintId}/burndown")
    //  public BurndownChartData getBurndownChart(@PathVariable Long sprintId) {
    //      // Fetch Burndown Chart data (number of tasks completed vs. remaining)
    //      return sprintService.generateBurndownChartData(sprintId);
    //  }




        @GetMapping("/{sprintId}/burndown")
        public ResponseEntity<Map<LocalDate, Integer>> getBurndown(@PathVariable Long sprintId) {
            return ResponseEntity.ok(sprintService.getBurndownData(sprintId));
        }


}
