package com.phaser.project.process;

import com.phaser.project.entities.ActionEntity;

import java.io.Console;
import java.util.Random;
import java.util.concurrent.Phaser;

public class BaseAction implements Runnable{

    Random rand = new Random();
    private Phaser phaser;
    private ActionEntity actionEntity;
    public void setPhaser(Phaser phaser){
        this.phaser = phaser;
        phaser.register();
    }
    public BaseAction(Phaser phaser, ActionEntity action){

       this.phaser = phaser;
       this.actionEntity = action;
    }


    @Override
    public void run(){
        if(phaser == null) return;

        phaser.register();
        System.out.println(actionEntity.getName() + " has started on phase " + phaser.getPhase());
        int delay = (int) (rand.nextInt((int) (actionEntity.getDuration()*0.1))+actionEntity.getDuration());
        try {
            Thread.sleep(delay);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(actionEntity.getName() + " has finished within " + delay + "ms");
        phaser.arriveAndDeregister();
    }
}
