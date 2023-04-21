package com.phaser.project.messagingstompwebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler myWebSocketHandler;

    public WebSocketConfig(MyWebSocketHandler myWebSocketHandler){
        this.myWebSocketHandler = myWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebHandler(), "/ws").setAllowedOrigins("*");
    }

    public WebSocketHandler myWebHandler() {
        return myWebSocketHandler;
    }
}