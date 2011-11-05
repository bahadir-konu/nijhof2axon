package org.axonframework.sample.app.query;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-04
 * Time: 11:28:37 AM
 */
public class LedgerEntry {

    public LedgerEntry(BigDecimal amount, String action) {
        this.amount = amount;
        this.action = action;
    }

    private BigDecimal amount;

    public String action;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
