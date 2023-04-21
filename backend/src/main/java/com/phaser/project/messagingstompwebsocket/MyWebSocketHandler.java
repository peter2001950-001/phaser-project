package com.phaser.project.messagingstompwebsocket;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
@Scope("singleton")
public class MyWebSocketHandler extends TextWebSocketHandler {

    private WebSocketSession session;
    public MyWebSocketHandler() {
        System.out.println("MyWebSocketHandler constructor called");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("got session: " + session);
        this.session = session;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("connection closed: " + status);
        this.session = null;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String clientMessage = message.getPayload();
        System.out.println("Received message: " + clientMessage);
        // Handle the message here

        String responseMessage = "Hello, client!";
        sendMessage(responseMessage);
    }

    public void sendMessage(String message) throws IOException {
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        } else {
            System.out.println("Cannot send message: connection is not open");
        }
    }
}
