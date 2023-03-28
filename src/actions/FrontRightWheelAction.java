package actions;

import java.util.concurrent.Phaser;

public class FrontRightWheelAction extends BaseAction{
    public FrontRightWheelAction() {
        super(1000, 1100);
    }

    @Override
    public String getActionName() {
        return "Front Right Wheel Action";
    }
}
