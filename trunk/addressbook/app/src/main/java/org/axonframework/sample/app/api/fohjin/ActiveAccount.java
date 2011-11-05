package org.axonframework.sample.app.api.fohjin;

import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.sample.app.api.Address;
import org.axonframework.sample.app.api.AddressType;
import org.axonframework.sample.app.api.fohjin.event.ActiveAccountOpenedEvent;
import org.axonframework.sample.app.api.fohjin.event.CashDepositedEvent;
import org.axonframework.sample.app.api.fohjin.event.MoneyTransferReceivedEvent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 10:44:13 AM
 */
public class ActiveAccount extends AbstractAnnotatedAggregateRoot {

    AggregateIdentifier clientId;
    String accountName;
    String accountNumber;
    BigDecimal balance = new BigDecimal(0);

    private List<Ledger> ledgers = new ArrayList<Ledger>();

    private Map<AddressType, Address> addresses = new HashMap<AddressType, Address>();

    public ActiveAccount(AggregateIdentifier identifier, AggregateIdentifier clientId, String accountName) {
        super(identifier);
        apply(new ActiveAccountOpenedEvent(clientId, accountName));
    }

    public ActiveAccount(AggregateIdentifier identifier) {
        super(identifier);
    }

    @EventHandler
    protected void handleMoneyTransferReceivedEvent(MoneyTransferReceivedEvent event) {

        ledgers.add(new DebitTransfer(event.getAmount(), event.getTargetAccountNumber()));
        balance = event.getNewBalance();
    }

    @EventHandler
    protected void handleCashDepositedEvent(CashDepositedEvent event) {

        balance = event.getNewBalance();
        ledgers.add(new CreditMutation(event.getAmount(), null));
    }

    public void receiveTransferFrom(String sourceAccountNumber, BigDecimal amount) {

        BigDecimal newBalance = balance.subtract(amount);

        apply(new MoneyTransferReceivedEvent(newBalance, amount, sourceAccountNumber, accountNumber));
    }

    public void depositCash(BigDecimal amount) {
        BigDecimal newBalance = balance.add(amount);

        apply(new CashDepositedEvent(getIdentifier(), newBalance, amount));

    }

}

