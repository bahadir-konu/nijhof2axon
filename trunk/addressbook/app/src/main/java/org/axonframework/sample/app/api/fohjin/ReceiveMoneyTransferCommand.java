package org.axonframework.sample.app.api.fohjin;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 3:09:53 PM
 */
public class ReceiveMoneyTransferCommand {

    private BigDecimal amount;
    private String accountNumber;

    public ReceiveMoneyTransferCommand(BigDecimal amount, String accountNumber) {
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
