package actions;

import java.util.concurrent.Phaser;

public class BackSeatAction extends BaseAction{
    public BackSeatAction() {
        super(3500, 5000);
    }

    @Override
    public String getActionName() {
        return "Back Seat Action";
    }
}
