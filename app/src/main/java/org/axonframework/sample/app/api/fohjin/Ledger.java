package org.axonframework.sample.app.api.fohjin;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 11:27:11 AM
 */
public class Ledger {

    private BigDecimal amount;

    public String accountNumber;

    public Ledger(BigDecimal amount, String accountNumber) {
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
