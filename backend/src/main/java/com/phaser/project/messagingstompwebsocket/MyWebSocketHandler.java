package com.phaser.project.messagingstompwebsocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phaser.project.domain.dto.SocketMessage;
import com.phaser.project.domain.dto.SocketMessageType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;

@Component
@Scope("singleton")
public class MyWebSocketHandler extends TextWebSocketHandler {

    private ArrayList<WebSocketSession> sessions = new ArrayList<>();
    public MyWebSocketHandler() {
        System.out.println("MyWebSocketHandler constructor called");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        System.out.println("got session: " + session);
        this.sessions.add(session);
        sendMessage(new SocketMessage(session.getId(), SocketMessageType.ConnectionEstablished), session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("connection closed: " + status);
        WebSocketSession existingSession = null;
        for (int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i).getId() == session.getId()){
                existingSession = sessions.get(i);
            }
        }
        sessions.remove(existingSession);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String clientMessage = message.getPayload();
        System.out.println("Received message: " + clientMessage);
        // Handle the message here

        String responseMessage = "Hello, client!";
        //sendMessage(responseMessage, session.getId());
    }

    public synchronized void sendMessage(SocketMessage message, String sessionId) throws IOException {

        WebSocketSession session = null;
        for (int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i).getId().equals(sessionId)){
                session = sessions.get(i);
            }
        }
        if (session != null && session.isOpen()) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(message);
            session.sendMessage(new TextMessage(jsonStr));
        } else {
            System.out.println("Cannot send message: connection is not open");
        }
    }
}
