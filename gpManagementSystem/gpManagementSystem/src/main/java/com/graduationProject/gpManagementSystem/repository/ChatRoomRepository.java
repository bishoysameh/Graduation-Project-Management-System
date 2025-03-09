package com.graduationProject.gpManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduationProject.gpManagementSystem.model.ChatRoom;
import com.graduationProject.gpManagementSystem.model.User;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByName(String name);


    //mmkn error
    List<ChatRoom> findByUsersContaining(Optional<User> user); // Change 'participants' to 'users'

}