package com.manindra.web_socket_app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/** Configures the WebSocket endpoint and the in-memory STOMP message broker. */
@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        log.info("Configuring simple WebSocket message broker: brokerPrefix=/topic, appPrefix=/app");
        // Messages sent to /topic are broadcast to every subscribed client.
        registry.enableSimpleBroker("/topic");
        // Messages sent by clients to /app are routed to @MessageMapping methods.
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        log.info("Registering STOMP WebSocket endpoint: /ws");
        // The browser first opens a WebSocket connection at /ws.
        registry.addEndpoint("/ws");
    }
}
