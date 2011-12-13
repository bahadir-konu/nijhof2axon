package nijhof2axon.ui.activeAccount;

import com.vaadin.ui.Table;
import nijhof2axon.ui.MediatorListener;
import nijhof2axon.ui.UIEvent;
import nijhof2axon.ui.MediatorVerticalLayout;
import nijhof2axon.ui.data.LedgerContainer;
import nijhof2axon.ui.events.ActiveAccountDetailsRequestedEvent;
import nijhof2axon.ui.events.LedgerModificationCompletedEvent;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class LedgerList extends MediatorVerticalLayout implements MediatorListener {
    LedgerContainer ledgerContainer;

    public LedgerList(LedgerContainer ledgerContainer) {

        this.ledgerContainer = ledgerContainer;

        final Table ledgersTable = new Table("Ledgers");
        ledgersTable.setContainerDataSource(ledgerContainer);

        ledgersTable.setVisibleColumns(new String[]{"action", "amount"});

        ledgersTable.setColumnHeader("action", "Action");
        ledgersTable.setColumnHeader("amount", "Amount");

        addComponent(ledgersTable);
    }

    @Override
    public void handleEvent(UIEvent event) {
        if (event instanceof ActiveAccountDetailsRequestedEvent) {
            ledgerContainer.refreshContent(((ActiveAccountDetailsRequestedEvent) event).getActiveAccountEntry().getIdentifier());
        }

        if (event instanceof LedgerModificationCompletedEvent) {
            ledgerContainer.refreshContent(((LedgerModificationCompletedEvent) event).getActiveAccountEntry().getIdentifier());
        }

    }
}
