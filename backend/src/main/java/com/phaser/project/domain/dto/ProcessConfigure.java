package com.phaser.project.domain.dto;

import com.phaser.project.messagingstompwebsocket.MyWebSocketHandler;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Phaser;

@Data
public class ProcessConfigure {
    private Phaser phaser;
    private MyWebSocketHandler myWebSocketHandler;
    private String sessionId;

    public ProcessConfigure(Phaser phaser, MyWebSocketHandler myWebSocketHandler, String id) {
        this.phaser = phaser;
        this.myWebSocketHandler = myWebSocketHandler;
        sessionId = id;
    }
}
