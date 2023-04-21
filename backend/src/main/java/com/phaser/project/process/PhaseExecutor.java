package com.phaser.project.process;

import com.phaser.project.entities.ActionEntity;
import com.phaser.project.messagingstompwebsocket.MyWebSocketHandler;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Phaser;

public class PhaseExecutor {

    private final List<ActionEntity> actions;
    private final Phaser phaser;
    private final ExecutorService executorService;
    private final MyWebSocketHandler myWebSocketHandler;

    public PhaseExecutor(List<ActionEntity> actions, Phaser phaser, ExecutorService executorService, MyWebSocketHandler myWebSocketHandler) {
        this.actions = actions;
        this.phaser = phaser;
        this.executorService = executorService;
        this.myWebSocketHandler = myWebSocketHandler;
    }

    public void start() {
        System.out.println("---- Phase " + phaser.getPhase() + " has started ----");
        //phaser.register();
        for (ActionEntity actionEntity : actions) {
            var action = new BaseAction(phaser, actionEntity, myWebSocketHandler);
            executorService.submit(action);
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("---- Phase " + (phaser.getPhase() - 1) + " is completed ----");
    }
}
