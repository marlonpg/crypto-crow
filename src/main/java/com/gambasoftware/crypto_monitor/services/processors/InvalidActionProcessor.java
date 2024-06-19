package com.gambasoftware.crypto_monitor.services.processors;

import com.gambasoftware.crypto_monitor.services.models.ActionModel;
import com.gambasoftware.crypto_monitor.services.models.ActionProcessor;
import com.gambasoftware.crypto_monitor.services.models.ActionType;
import org.springframework.stereotype.Service;

@Service
public class InvalidActionProcessor implements ActionProcessor {
    @Override
    public String process(ActionModel actionModel) {
        return null;
    }

    @Override
    public ActionType getType() {
        return ActionType.INVALID;
    }
}
