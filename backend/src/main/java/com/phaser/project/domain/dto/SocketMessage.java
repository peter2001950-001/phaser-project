package com.phaser.project.domain.dto;

import lombok.Data;

@Data
public class SocketMessage {
    public SocketMessage(String message, SocketMessageType type){
        this.message = message;
        this.type = type;
    }
    private String message;
    private int duration;

    private SocketMessageType type;

}
