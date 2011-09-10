package org.axonframework.sample.app.api.fohjin;

import org.axonframework.domain.DomainEvent;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 10:56:04 AM
 */
public class MoneyTransferReceivedEvent extends DomainEvent {
    BigDecimal newBalance;
    BigDecimal amount;
    String sourceAccountNumber;
    String targetAccountNumber;

    public MoneyTransferReceivedEvent(BigDecimal newBalance, BigDecimal amount, String sourceAccountNumber, String accountNumber) {

    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }
}
