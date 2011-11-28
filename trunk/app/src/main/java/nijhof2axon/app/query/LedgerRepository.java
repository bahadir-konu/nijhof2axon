package nijhof2axon.app.query;

import java.util.List;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-04
 * Time: 11:30:13 AM
 */
public interface LedgerRepository {
    List<LedgerEntry> findAllLedgers();

    List<LedgerEntry> findByAccountId(String activeAccountId);
}
