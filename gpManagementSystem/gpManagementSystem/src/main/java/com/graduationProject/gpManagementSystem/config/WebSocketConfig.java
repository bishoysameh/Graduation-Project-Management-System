package com.graduationProject.gpManagementSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        // registry.enableSimpleBroker("/topic");


        // Enable topic-based messaging (for specific chat rooms)
    registry.enableSimpleBroker("/topic", "/chatroom");
    }
}

































// import org.springframework.context.annotation.Configuration;
// import org.springframework.lang.NonNull;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
// import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
// import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// @Configuration
// @EnableWebSocketMessageBroker
// public class WebSocketConfig implements WebSocketMessageBrokerConfigurer   {

//     @Override
//     public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
//         registry.addEndpoint("/ws")
//                  .setAllowedOrigins("*") // ✅ Allow all origins
//                  .withSockJS(); // ✅ Enable SockJS for better browser compatibility
//     }

//     @Override
//     public void configureMessageBroker(@NonNull MessageBrokerRegistry registry) {
//         registry.enableSimpleBroker("/topic");  // ✅ Broker for messages
//         registry.setApplicationDestinationPrefixes("/app"); // ✅ Prefix for client messages
//     }

// }





