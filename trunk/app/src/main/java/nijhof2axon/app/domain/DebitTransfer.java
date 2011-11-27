package nijhof2axon.app.domain;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 11:27:42 AM
 */
public class DebitTransfer extends Ledger {
    public DebitTransfer(BigDecimal amount, String accountNumber) {
        super(amount, accountNumber);
    }
}
