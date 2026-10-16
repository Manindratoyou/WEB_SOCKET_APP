package com.manindra.web_socket_app.chat.dto;

/** Message exchanged between browser clients and the server. */
public record ChatMessage(String sender, String content, String sentAt) {
}
