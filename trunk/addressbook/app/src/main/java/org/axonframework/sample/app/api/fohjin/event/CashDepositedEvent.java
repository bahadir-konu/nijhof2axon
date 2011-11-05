package org.axonframework.sample.app.api.fohjin.event;

import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.domain.DomainEvent;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-05
 * Time: 11:17:50 AM
 */
public class CashDepositedEvent extends DomainEvent {

    public CashDepositedEvent(AggregateIdentifier identifier, BigDecimal newBalance, BigDecimal amount) {

        //BKONU: is this ok? I m not sure..
        super(1, identifier);

        this.newBalance = newBalance;
        this.amount = amount;
    }

    private BigDecimal newBalance;
    private BigDecimal amount;

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
