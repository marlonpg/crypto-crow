package com.gambasoftware.crypto_monitor.services;

import com.gambasoftware.crypto_monitor.repository.models.Transaction;
import com.gambasoftware.crypto_monitor.services.models.ActionModel;
import org.springframework.stereotype.Component;

@Component
public class TransactionsConverter {

    public Transaction convert(ActionModel actionModel) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(actionModel.getAction().name());
        transaction.setUserId(actionModel.getUserId());
        transaction.setSymbol(actionModel.getSymbol());
        transaction.setAmount(actionModel.getAmount());
        transaction.setPrice(actionModel.getPrice());
        transaction.setAssetType(actionModel.getAssetType());
        //TODO: allow old transactions dates
        //transaction.setPurchaseDateTime(actionModel.getDate());

        return transaction;
    }
}
