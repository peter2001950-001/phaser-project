package actions;

import java.util.concurrent.Phaser;

public class FrontRightSeatAction extends BaseAction{
    public FrontRightSeatAction() {
        super(1500, 2500);
    }

    @Override
    public String getActionName() {
        return "Front Right Seat Action" ;
    }
}
