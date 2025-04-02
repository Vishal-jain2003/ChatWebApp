package com.substring.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${frontend.url}")  // Get URL from application.properties or environment variable
    private String frontendUrl;


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        // topic/messages
        config.setApplicationDestinationPrefixes("/app");

        // /app/chat
        // server side : @MessageMapping("/chat")

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       registry.addEndpoint("/chat")
               .setAllowedOrigins(frontendUrl)
               .withSockJS();
    }
    // we will established our connection in this /chat means it been subscribed  endpoint and we will use sockjs


}
//This setup allows real-time chat where messages can be sent and received instantly.
//STOMP helps manage chat topics and message delivery.
//SockJS ensures compatibility for older browsers.
//Frontend (React) connects to /chat and subscribes to /topic/messages to receive real-time updates.

//how it works ????
//Frontend connects to /chat endpoint using WebSockets.
//Users send messages to /app/chat (handled by a server-side method @MessageMapping("/chat")).
//Messages are broadcasted to /topic/messages, and clients subscribed to this topic will receive the messages instantly.
