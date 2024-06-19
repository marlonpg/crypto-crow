package com.gambasoftware.crypto_monitor.services.processors;

import com.gambasoftware.crypto_monitor.services.models.ActionProcessor;
import com.gambasoftware.crypto_monitor.services.models.ActionType;
import jakarta.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class ActionProcessorFactory {
    @Inject
    private BuyActionProcessor buyActionProcessor;
    @Inject
    private InvalidActionProcessor invalidActionProcessor;

    public ActionProcessor get(ActionType actionType) {
        switch (actionType) {
            case BUY -> {
                return buyActionProcessor;
            }
            default -> {
                return invalidActionProcessor;
            }
        }
    }
}
