package actions;

import java.util.concurrent.Phaser;

public class BackRightWheelAction extends BaseAction{
    public BackRightWheelAction() {
        super(1000, 1100);
    }

    @Override
    public String getActionName() {
        return "Back Right Wheel Action";
    }
}
