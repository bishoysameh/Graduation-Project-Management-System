package com.graduationProject.gpManagementSystem.service;

import org.springframework.stereotype.Service;
import com.graduationProject.gpManagementSystem.model.ChatRoom;
import com.graduationProject.gpManagementSystem.model.User;
import com.graduationProject.gpManagementSystem.repository.ChatRoomRepository;
import com.graduationProject.gpManagementSystem.repository.UserRepository;

import java.util.List;

@Service
public class ChatRoomService {
    
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository , UserRepository userRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    // ✅ Create a new chat room
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    // ✅ Get a chat room by ID
    public ChatRoom getChatRoomById(Long id) {
        return chatRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
    }

    // ✅ Get all chat rooms
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    // ✅ Update a chat room
    public ChatRoom updateChatRoom(Long id, ChatRoom updatedChatRoom) {
        ChatRoom chatRoom = getChatRoomById(id);
        chatRoom.setName(updatedChatRoom.getName()); // Update only the name
        return chatRoomRepository.save(chatRoom);
    }

    // ✅ Delete a chat room
    public void deleteChatRoom(Long id) {
        chatRoomRepository.deleteById(id);
    }


    public void addUserToChatRoom(Long chatRoomId, Long userId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat Room not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        chatRoom.getUsers().add(user);
        chatRoomRepository.save(chatRoom);
    }
}
