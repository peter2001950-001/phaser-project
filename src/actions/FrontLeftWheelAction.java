package actions;

import java.util.concurrent.Phaser;

public class FrontLeftWheelAction  extends BaseAction{
    public FrontLeftWheelAction() {
        super(1000, 1100);
    }

    @Override
    public String getActionName() {
        return "Front Left Wheel Action";
    }
}
