package org.axonframework.sample.app.query;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-04
 * Time: 11:28:37 AM
 */
public class LedgerEntry {

    private BigDecimal amount;

    public String accountNumber;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
