package com.phaser.project.process;

import com.phaser.project.controllers.MainController;
import com.phaser.project.entities.ActionEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.Console;
import java.util.Random;
import java.util.concurrent.Phaser;

public class BaseAction implements Runnable{
    private SimpMessagingTemplate messagingTemplate;

    Random rand = new Random();
    private Phaser phaser;
    private ActionEntity actionEntity;
    public BaseAction(Phaser phaser, ActionEntity action){

       this.phaser = phaser;
       this.actionEntity = action;
       this.phaser.register();
    }


    @Override
    public void run(){
        if(phaser == null) return;

        System.out.println(actionEntity.getName() + " has started on phase " + phaser.getPhase());
        int delay = (int) (rand.nextInt((int) (actionEntity.getDuration()*0.1))+actionEntity.getDuration());
        try {
            Thread.sleep(delay);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        messagingTemplate.convertAndSend("/topic", "hoo00");
        System.out.println(actionEntity.getName() + " has finished within " + delay + "ms");
        phaser.arriveAndDeregister();
    }

}
