package com.gambasoftware.crypto_monitor.services.models;

public interface ActionProcessor {
    String process(ActionModel actionModel);
    ActionType getType();
}
