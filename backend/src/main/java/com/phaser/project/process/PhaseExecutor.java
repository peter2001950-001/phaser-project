package com.phaser.project.process;

import com.phaser.project.domain.dto.ProcessConfigure;
import com.phaser.project.domain.dto.SocketMessage;
import com.phaser.project.domain.dto.SocketMessageType;
import com.phaser.project.entities.ActionEntity;
import com.phaser.project.entities.PhaseEntity;
import com.phaser.project.messagingstompwebsocket.MyWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Phaser;

public class PhaseExecutor {

    private final List<ActionEntity> actions;
    private final ProcessConfigure processConfigure;
    private final ExecutorService executorService;
    private final PhaseEntity phase;
    public PhaseExecutor(PhaseEntity phase, List<ActionEntity> actions, ExecutorService executorService, ProcessConfigure processConfigure) {
        this.actions = actions;

        this.processConfigure = processConfigure;
        this.executorService = executorService;
        this.phase = phase;
    }

    public void start() throws IOException {
        startEvent();
        //phaser.register();
        for (ActionEntity actionEntity : actions) {
            var action = new BaseAction(actionEntity, processConfigure);
            executorService.submit(action);
        }
        processConfigure.getPhaser().arriveAndAwaitAdvance();
        endEvent();
    }


    public void startEvent() throws IOException {
        System.out.println("---- Phase " + processConfigure.getPhaser().getPhase() + " has started ----");
        var message = new SocketMessage(phase.getId().toString(), SocketMessageType.PhaseStart);
        processConfigure.getMyWebSocketHandler().sendMessage(message, processConfigure.getSessionId());

    }

    public void endEvent() throws IOException {
        System.out.println("---- Phase " + (processConfigure.getPhaser().getPhase() - 1) + " is completed ----");
        var message = new SocketMessage(phase.getId().toString(), SocketMessageType.PhaseEnded);
        processConfigure.getMyWebSocketHandler().sendMessage(message, processConfigure.getSessionId());

    }
}
