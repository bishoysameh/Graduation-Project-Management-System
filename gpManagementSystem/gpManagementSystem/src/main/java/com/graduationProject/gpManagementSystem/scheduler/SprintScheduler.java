package com.graduationProject.gpManagementSystem.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.graduationProject.gpManagementSystem.enums.SprintStatus;
import com.graduationProject.gpManagementSystem.model.Sprint;
import com.graduationProject.gpManagementSystem.repository.SprintRepository;
import com.graduationProject.gpManagementSystem.service.SprintService;

@Component
@EnableScheduling
public class SprintScheduler {

    @Autowired
    private SprintService sprintService;

    @Autowired
    private SprintRepository sprintRepository;

    // Runs every hour to check for expired sprints
    @Scheduled(cron = "0 0 * * * ?")
    public void checkAndEndSprints() {
        List<Sprint> activeSprints = sprintRepository.findByStatus(SprintStatus.ACTIVE);
        LocalDateTime now = LocalDateTime.now();

        for (Sprint sprint : activeSprints) {
            if (sprint.getEndTime().isBefore(now)) {
                sprintService.endSprint(sprint.getId());
                System.out.println("Sprint " + sprint.getId() + " has been automatically ended.");
            }
        }
    }
}
