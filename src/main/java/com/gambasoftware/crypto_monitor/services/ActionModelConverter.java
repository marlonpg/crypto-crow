package com.gambasoftware.crypto_monitor.services;

import com.gambasoftware.crypto_monitor.services.models.ActionModel;
import com.gambasoftware.crypto_monitor.services.models.ActionType;
import org.springframework.stereotype.Component;

@Component
public class ActionModelConverter {

    public ActionModel convert(String userId, String userName, String message) {
        ActionModel actionModel = new ActionModel();
        actionModel.setUserId(userId);
        actionModel.setUserName(userName);

        String[] messageParts = message.split(" ");

        if (messageParts.length > 0) {
            actionModel.setAction(ActionType.get(messageParts[0]));
            actionModel.setSymbol(messageParts[1]);
            actionModel.setPrice(messageParts[2]);
            actionModel.setAmount(messageParts[3]);
            if (messageParts.length > 4 && messageParts[4] != null) {
                actionModel.setAssetType(messageParts[4]);
            }
        }

        return actionModel;
    }
}
