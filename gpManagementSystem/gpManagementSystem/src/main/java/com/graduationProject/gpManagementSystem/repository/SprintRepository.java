package com.graduationProject.gpManagementSystem.repository;


import com.graduationProject.gpManagementSystem.enums.SprintStatus;
import com.graduationProject.gpManagementSystem.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
    List<Sprint> findByProjectId(Long projectId);
        List<Sprint> findByStatus(SprintStatus status);

}
