package com.graduationProject.gpManagementSystem.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.graduationProject.gpManagementSystem.dto.ApiResponse;
import com.graduationProject.gpManagementSystem.model.ChatRoom;
import com.graduationProject.gpManagementSystem.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;



    
    // ✅ Create a new chat room
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ChatRoom>> createChatRoom(@RequestBody ChatRoom chatRoom) {
        ChatRoom createdChatRoom = chatRoomService.createChatRoom(chatRoom);
         
    ApiResponse<ChatRoom> response = new ApiResponse<>(
        "success", 
        "Chat room created successfully", 
        createdChatRoom
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    // ✅ Get a chat room by ID
    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom> getChatRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(chatRoomService.getChatRoomById(id));
    }




    // ✅ Get all chat rooms
    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        return ResponseEntity.ok(chatRoomService.getAllChatRooms());
    }



    // ✅ Update a chat room
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ChatRoom>> updateChatRoom(@PathVariable Long id, @RequestBody ChatRoom chatRoom) {
        ChatRoom updatedChatRoom =  chatRoomService.updateChatRoom(id, chatRoom);
        ApiResponse<ChatRoom> response = new ApiResponse<>(
            "success", 
            "Chat room updated successfully", 
            updatedChatRoom
        );
    
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




    // ✅ Delete a chat room
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteChatRoom(@PathVariable Long id) {
       chatRoomService.deleteChatRoom(id);

       ApiResponse<Void> successResponse = new ApiResponse<>(
        "success",
        "chat room deleted successfully"
        );
    return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }




    @PostMapping("/{chatRoomId}/addUser/{userId}")
    public ResponseEntity<ApiResponse<Void>> addUserToChatRoom(@PathVariable Long chatRoomId, @PathVariable Long userId) {
       chatRoomService.addUserToChatRoom(chatRoomId, userId);
            ApiResponse<Void> response = new ApiResponse<>(
                "success",
                "User added to chat room successfully"
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
            }
}
