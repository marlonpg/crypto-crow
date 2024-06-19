package com.gambasoftware.crypto_monitor.services.processors;

import com.gambasoftware.crypto_monitor.repository.models.Transaction;
import com.gambasoftware.crypto_monitor.services.TransactionService;
import com.gambasoftware.crypto_monitor.services.TransactionsConverter;
import com.gambasoftware.crypto_monitor.services.models.ActionModel;
import com.gambasoftware.crypto_monitor.services.models.ActionProcessor;
import com.gambasoftware.crypto_monitor.services.models.ActionType;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BuyActionProcessor implements ActionProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuyActionProcessor.class);

    @Inject
    private TransactionService transactionService;
    @Inject
    private TransactionsConverter transactionsConverter;

    @Override
    public String process(ActionModel actionModel) {
        LOGGER.info("Processing action requested by user: {}", actionModel);

        Transaction transaction = transactionService.add(transactionsConverter.convert(actionModel));

        return String.format("New transaction added for %s, you've bought %s at price %s", transaction.getSymbol(), transaction.getAmount(), transaction.getPrice());
    }

    @Override
    public ActionType getType() {
        return ActionType.BUY;
    }
}
