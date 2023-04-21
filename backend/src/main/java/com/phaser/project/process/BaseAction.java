package com.phaser.project.process;

import com.phaser.project.entities.ActionEntity;
import com.phaser.project.messagingstompwebsocket.MyWebSocketHandler;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.Phaser;

public class BaseAction implements Runnable{

    Random rand = new Random();
    private final Phaser phaser;
    private final ActionEntity actionEntity;
    private final MyWebSocketHandler myWebSocketHandler;
    public BaseAction(Phaser phaser, ActionEntity action, MyWebSocketHandler myWebSocketHandler){
       this.phaser = phaser;
       this.actionEntity = action;
       this.phaser.register();
       this.myWebSocketHandler = myWebSocketHandler;
    }


    @SneakyThrows
    @Override
    public void run(){
        if(phaser == null) return;

        System.out.println(actionEntity.getName() + " has started on phase " + phaser.getPhase());
        myWebSocketHandler.sendMessage(actionEntity.getName() + " has started on phase " + phaser.getPhase());
        int delay = (int) (rand.nextInt((int) (actionEntity.getDuration()*0.1))+actionEntity.getDuration());
        try {
            Thread.sleep(delay);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(actionEntity.getName() + " has finished within " + delay + "ms");
        myWebSocketHandler.sendMessage(actionEntity.getName() + " has finished within " + delay + "ms");
        phaser.arriveAndDeregister();
    }

}
