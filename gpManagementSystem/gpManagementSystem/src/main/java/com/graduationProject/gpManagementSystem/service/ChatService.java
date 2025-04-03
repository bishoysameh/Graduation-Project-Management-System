package com.graduationProject.gpManagementSystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.graduationProject.gpManagementSystem.enums.MessageType;
import com.graduationProject.gpManagementSystem.exception.ResourceNotFoundException;
import com.graduationProject.gpManagementSystem.model.ChatRoom;
import com.graduationProject.gpManagementSystem.model.Message;
import com.graduationProject.gpManagementSystem.model.User;
import com.graduationProject.gpManagementSystem.repository.ChatRoomRepository;
import com.graduationProject.gpManagementSystem.repository.MessageRepository;
import com.graduationProject.gpManagementSystem.repository.UserRepository;


@Service
public class ChatService {
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatService(
            UserRepository userRepository,
            ChatRoomRepository chatRoomRepository,
            MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.messageRepository = messageRepository;
    }


    public Message sendMessage(Long chatRoomId, Long senderId, String content , MessageType type ) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
            .orElseThrow(() -> new ResourceNotFoundException("ChatRoom not found"));
        User sender = userRepository.findById(senderId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setSender(sender);
        message.setContent(content);
        message.setType(type);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }











    public List<Message> getChatMessages(Long chatRoomId) {
        return messageRepository.findByChatRoomId(chatRoomId);
    }





    //enhance it to clean code
    public List<ChatRoom> getUserChatRooms(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return chatRoomRepository.findByUsersContaining(user);
    }
}

