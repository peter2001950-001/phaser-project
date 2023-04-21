package com.phaser.project.controllers;

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
    public ResponseEntity<?> RunProcess() throws IOException {

        Phaser phaser = new Phaser(1);
        var phases = phaseRepository.findAllOrderByPhaseOrder();
        var phaseExecutors = new ArrayList<PhaseExecutor>();
        var executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < phases.size(); i++) {
            var actions = actionRepository.findAllByPhase_Id(phases.get(i).getId());
            var executor = new PhaseExecutor(actions, phaser, executorService, webSocketHandler);
            executor.start();
        }
        return ResponseEntity.ok().build();
    }
}
