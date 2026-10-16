package com.manindra.web_socket_app.chat.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/** Logs WebSocket client connection lifecycle events. */
@Component
@Slf4j
public class WebSocketEventListener {

    @EventListener
    public void onClientConnected(SessionConnectedEvent event) {
        log.info("WebSocket client connected: sessionId={}", event.getMessage().getHeaders().get("simpSessionId"));
    }

    @EventListener
    public void onClientDisconnected(SessionDisconnectEvent event) {
        log.info("WebSocket client disconnected: sessionId={}", event.getSessionId());
    }
}
