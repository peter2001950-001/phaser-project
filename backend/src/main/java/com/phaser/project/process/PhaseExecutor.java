package com.phaser.project.process;

import com.phaser.project.entities.ActionEntity;
import com.phaser.project.entities.PhaseEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaseExecutor {

    public PhaseExecutor(List<ActionEntity> actions, Phaser phaser, ExecutorService executorService){

        this.actions = actions;
        this.phaser = phaser;
        this.executorService = executorService;
    }
    private List<ActionEntity> actions;
    private Phaser phaser;
    private ExecutorService executorService;

    public void start(){
        System.out.println("---- Phase " + phaser.getPhase() + " has started ----");
        for (int i = 0; i < actions.size(); i++) {
            var action = new BaseAction(phaser, actions.get(i));
            executorService.submit(action);
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("---- Phase " + (phaser.getPhase()-1) + " is completed ----");
    }
}
