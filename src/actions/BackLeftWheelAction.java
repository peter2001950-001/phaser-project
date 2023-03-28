package actions;

import java.util.concurrent.Phaser;

public class BackLeftWheelAction extends BaseAction{
    public BackLeftWheelAction() {
        super(1000, 1100);
    }

    @Override
    public String getActionName() {
        return "Back Left Wheel Action";
    }
}
