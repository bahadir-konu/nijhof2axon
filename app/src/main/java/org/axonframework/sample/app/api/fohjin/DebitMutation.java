package org.axonframework.sample.app.api.fohjin;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-19
 * Time: 12:09:05 PM
 */
public class DebitMutation extends Ledger {
    public DebitMutation(BigDecimal amount, String accountNumber) {
        super(amount, null);
    }
}
