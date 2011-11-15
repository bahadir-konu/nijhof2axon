package org.axonframework.sample.app.api.fohjin.event;

import org.axonframework.domain.DomainEvent;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-05
 * Time: 11:17:50 AM
 */
public class CashDepositedEvent extends DomainEvent {

    public CashDepositedEvent(BigDecimal newBalance, BigDecimal amount) {
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
