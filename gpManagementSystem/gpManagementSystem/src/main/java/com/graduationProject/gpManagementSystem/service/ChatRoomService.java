package com.graduationProject.gpManagementSystem.service;


import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.exception.ResourceNotFoundException;
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
    // public ChatRoom createChatRoom(ChatRoom chatRoom) {
    //     return chatRoomRepository.save(chatRoom);
    // }

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
    // Save the chat room
    chatRoom = chatRoomRepository.save(chatRoom);
   
    return chatRoom;
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
    // public ChatRoom updateChatRoom(Long id, ChatRoom updatedChatRoom) {
    //     ChatRoom chatRoom = getChatRoomById(id);
    //     chatRoom.setName(updatedChatRoom.getName()); // Update only the name
    //     return chatRoomRepository.save(chatRoom);
    // }
public ChatRoom updateChatRoom(Long id, ChatRoom updatedChatRoom) {

    ChatRoom chatRoom = chatRoomRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ChatRoom with id " + id + " not found"));

    chatRoom.setName(updatedChatRoom.getName()); 

    ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom); 

    return savedChatRoom;
   
}










    // ✅ Delete a chat room
    // public void deleteChatRoom(Long id) {
    //     chatRoomRepository.deleteById(id);
    // }

    public void deleteChatRoom(Long id ){
        chatRoomRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("chat room not found"));
           chatRoomRepository.deleteById(id);
       }






    public void addUserToChatRoom(Long chatRoomId, Long userId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new ResourceNotFoundException("Chat Room not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        chatRoom.getUsers().add(user);
        chatRoomRepository.save(chatRoom);

        }
}
