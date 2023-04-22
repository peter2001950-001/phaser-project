package com.phaser.project.controllers;

import com.phaser.project.domain.dto.ProcessConfigure;
import com.phaser.project.domain.dto.RunRequest;
import com.phaser.project.domain.dto.SocketMessage;
import com.phaser.project.domain.dto.SocketMessageType;
import com.phaser.project.messagingstompwebsocket.MyWebSocketHandler;
import com.phaser.project.process.PhaseExecutor;
import com.phaser.project.repositories.ActionRepository;
import com.phaser.project.repositories.PhaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final PhaseRepository phaseRepository;
    private final ActionRepository actionRepository;
    private final MyWebSocketHandler webSocketHandler;
    @PostMapping("run")
    public ResponseEntity<?> RunProcess(@RequestBody RunRequest request) throws IOException {
        if(request.getId().isBlank()) return ResponseEntity.badRequest().build();

        CompletableFuture<?> future
                = CompletableFuture.supplyAsync(() -> {
            try {
                return StartProcess(request);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return ResponseEntity.ok().build();
    }

    public String StartProcess(RunRequest request) throws IOException {
        startEvent(request.getId());
        Phaser phaser = new Phaser(1);
        var phases = phaseRepository.findAllOrderByPhaseOrder();
        var executorService = Executors.newCachedThreadPool();
        var configure = new ProcessConfigure(phaser, webSocketHandler, request.getId());

        for (int i = 0; i < phases.size(); i++) {
            var actions = actionRepository.findAllByPhase_Id(phases.get(i).getId());
            var executor = new PhaseExecutor(phases.get(i), actions, executorService, configure);
            executor.start();
        }
        endEvent(request.getId());
        return null;
    }
    public void startEvent(String sessionId) throws IOException {
        System.out.println("Process has started");
        var message = new SocketMessage(null,  SocketMessageType.ProcessStarted);
        webSocketHandler.sendMessage(message, sessionId);

    }

    public void endEvent(String sessionId) throws IOException {
        System.out.println("Process has finished");
        var message = new SocketMessage(null,  SocketMessageType.ProcessEnded);
        webSocketHandler.sendMessage(message, sessionId);

    }
}
