package actions;

import java.util.concurrent.Phaser;

public class FrontBridgeAction extends BaseAction {
    public FrontBridgeAction() {
        super(1000, 2000);
    }

    @Override
    public String getActionName() {
        return "Front Bridge action";
    }
}
