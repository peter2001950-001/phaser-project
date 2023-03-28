package actions;

import java.util.concurrent.Phaser;

public class FrontLeftSeatAction extends BaseAction{
    public FrontLeftSeatAction() {
        super(1500, 2500);
    }

    @Override
    public String getActionName() {
        return "Front Left Seat Action";
    }
}
