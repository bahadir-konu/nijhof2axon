package org.axonframework.sample.app.api.fohjin.command;

import java.math.BigDecimal;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class DepositCashCommand {

    private String activeAccountId;

    private BigDecimal amount;

    public DepositCashCommand(String activeAccountId, BigDecimal amount) {
        this.activeAccountId = activeAccountId;
        this.amount = amount;
    }

    public String getActiveAccountId() {
        return activeAccountId;
    }

    public void setActiveAccountId(String activeAccountId) {
        this.activeAccountId = activeAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
