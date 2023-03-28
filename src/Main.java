import actions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class Main {
    public  static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args)
    {
        Phaser phaser = new Phaser(1);

        Phase bridgePlacementPhase = new Phase(Arrays.asList(new FrontBridgeAction(), new BackBridgeAction()), phaser, executorService);
        bridgePlacementPhase.start();

        Phase wheelPlacementPhase = new Phase(Arrays.asList(new FrontLeftWheelAction(), new FrontRightWheelAction(),
                new BackLeftWheelAction(), new BackRightWheelAction()), phaser, executorService);
        wheelPlacementPhase.start();

        Phase seatsPlacementPhase = new Phase(Arrays.asList(new FrontLeftSeatAction(), new FrontRightSeatAction(),
                new BackSeatAction()), phaser, executorService);
        seatsPlacementPhase.start();

    }
}