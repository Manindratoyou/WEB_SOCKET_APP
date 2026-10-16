package com.manindra.web_socket_app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class WebSocketAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketAppApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void logApplicationReady() {
        log.info("WebSocket chat application is ready. Open http://localhost:8080");
    }

}
