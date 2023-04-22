package com.phaser.project.process;

import com.phaser.project.domain.dto.ProcessConfigure;
import com.phaser.project.domain.dto.SocketMessage;
import com.phaser.project.domain.dto.SocketMessageType;
import com.phaser.project.entities.ActionEntity;
import com.phaser.project.messagingstompwebsocket.MyWebSocketHandler;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Phaser;

public class BaseAction implements Runnable{

    Random rand = new Random();
    private final ActionEntity actionEntity;
    private final ProcessConfigure processConfigure;
    public BaseAction( ActionEntity action, ProcessConfigure processConfigure){
       this.actionEntity = action;
       this.processConfigure = processConfigure;
       processConfigure.getPhaser().register();
    }


    @SneakyThrows
    @Override
    public void run(){
        if(processConfigure.getPhaser() == null) return;

        try {
            startEvent();
           int delay = (int) (rand.nextInt((int) (actionEntity.getDuration() * 0.1)) + actionEntity.getDuration());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            endEvent(delay);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void startEvent() throws IOException {
        System.out.println(actionEntity.getName() + " has started on phase " + processConfigure.getPhaser().getPhase());
        var message = new SocketMessage(actionEntity.getId().toString(), SocketMessageType.ActionStart);
        processConfigure.getMyWebSocketHandler().sendMessage(message, processConfigure.getSessionId());

    }

    public void endEvent(int delay) throws IOException {
        System.out.println(actionEntity.getName() + " has finished within " + delay + "ms");
        var message = new SocketMessage(actionEntity.getId().toString(), SocketMessageType.ActionFinish);
        message.setDuration(delay);
        processConfigure.getMyWebSocketHandler().sendMessage(message, processConfigure.getSessionId());
        processConfigure.getPhaser().arriveAndDeregister();

    }


}
