package com.gambasoftware.crypto_monitor.services.models;

import java.util.Arrays;

public enum ActionType {
    BUY,
    SELL,
    ALERT,
    DELETE,
    INVALID;

    public static ActionType get(String s) {
        return Arrays.stream(values()).filter(actionType -> actionType.toString().equalsIgnoreCase(s))
                .findFirst().orElse(null);
    }
}
