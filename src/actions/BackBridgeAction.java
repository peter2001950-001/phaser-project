package actions;

import java.util.concurrent.Phaser;

public class BackBridgeAction extends BaseAction {
    public BackBridgeAction() {
        super(500, 1500);
    }

    @Override
    public String getActionName() {
        return "Back bridge action";
    }
}
