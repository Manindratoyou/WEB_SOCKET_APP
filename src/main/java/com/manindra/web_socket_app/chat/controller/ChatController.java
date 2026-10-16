package com.manindra.web_socket_app.chat.controller;

import java.time.Instant;

import com.manindra.web_socket_app.chat.dto.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/** Receives client messages and broadcasts them to all chat subscribers. */
@Controller
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage incoming) {
        String sender = incoming.sender() == null || incoming.sender().isBlank()
                ? "Anonymous"
                : incoming.sender().trim();
        String content = incoming.content() == null ? "" : incoming.content().trim();

        if (content.isEmpty()) {
            log.warn("Ignoring empty chat message from sender={}", sender);
            return;
        }

        ChatMessage message = new ChatMessage(sender, content, Instant.now().toString());
        log.info("Broadcasting chat message: sender={}, destination=/topic/messages", sender);
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
