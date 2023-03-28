package actions;

import java.io.Console;
import java.util.Random;
import java.util.concurrent.Phaser;

public abstract class BaseAction implements Runnable{

    Random rand = new Random();
    private Phaser phaser;
    private int minDelay;
    private int maxDelay;
    public abstract String getActionName();
    public void setPhaser(Phaser phaser){
        this.phaser = phaser;
        phaser.register();
    }
    public BaseAction(int minDelay, int maxDelay){

       this.maxDelay = maxDelay;
       this.minDelay = minDelay;
    }


    @Override
    public void run(){
        if(phaser == null) return;

        System.out.println(getActionName() + " has started on phase " + phaser.getPhase());
        var delay = rand.nextInt(maxDelay)+minDelay;
        try {
            Thread.sleep(delay);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(getActionName() + " has finished within " + delay + "ms");
        phaser.arriveAndDeregister();
    }
}
