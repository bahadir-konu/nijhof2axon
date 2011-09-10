package org.axonframework.sample.app.api.fohjin;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 3:09:53 PM
 */
public class ReceiveMoneyTransferCommand {

    private String activeAccountId;
    private BigDecimal amount;
    private String accountNumber;

    public ReceiveMoneyTransferCommand(String activeAccountId, BigDecimal amount, String accountNumber) {
        this.activeAccountId = activeAccountId;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getActiveAccountId() {
        return activeAccountId;
    }
}
