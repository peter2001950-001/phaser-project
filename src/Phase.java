import actions.BaseAction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class Phase {

    public Phase(List<BaseAction> actions, Phaser phaser, ExecutorService executorService){

        this.actions = actions;
        this.phaser = phaser;
        this.executorService = executorService;
    }
    private List<BaseAction> actions;
    private Phaser phaser;
    private ExecutorService executorService;

    public void start(){
        System.out.println("---- Phase " + phaser.getPhase() + " has started ----");
        for (int i = 0; i < actions.size(); i++) {
            actions.get(i).setPhaser(phaser);
            executorService.submit(actions.get(i));
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("---- Phase " + (phaser.getPhase()-1) + " is completed ----");
    }
}
