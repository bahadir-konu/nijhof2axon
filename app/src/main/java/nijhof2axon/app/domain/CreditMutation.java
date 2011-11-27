package nijhof2axon.app.domain;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-05
 * Time: 11:33:56 AM
 */
public class CreditMutation extends Ledger {
    public CreditMutation(BigDecimal amount, String accountNumber) {
        super(amount, null);
    }
}
