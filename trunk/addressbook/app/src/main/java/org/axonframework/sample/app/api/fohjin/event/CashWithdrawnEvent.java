package org.axonframework.sample.app.api.fohjin.event;

import org.axonframework.domain.DomainEvent;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-19
 * Time: 11:58:19 AM
 */
public class CashWithdrawnEvent extends DomainEvent {

    private String activeAccountId;
    private BigDecimal newBalance;
    private BigDecimal amount;

    public CashWithdrawnEvent(String activeAccountId, BigDecimal newBalance, BigDecimal amount) {
        this.activeAccountId = activeAccountId;
        this.newBalance = newBalance;
        this.amount = amount;
    }


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

    public String getActiveAccountId() {
        return activeAccountId;
    }

    public void setActiveAccountId(String activeAccountId) {
        this.activeAccountId = activeAccountId;
    }
}
