package com.graduationProject.gpManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduationProject.gpManagementSystem.model.Message;

@Repository
public interface MessageRepository extends JpaRepository <Message, Long> {
    List<Message> findByChatRoomId(Long chatRoomId);
    List<Message> findByChatRoomIdOrderByTimestamp(Long chatRoomId);

}
