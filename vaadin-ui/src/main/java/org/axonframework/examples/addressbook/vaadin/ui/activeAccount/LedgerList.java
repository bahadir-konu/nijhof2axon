package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.ui.Table;
import nijhof2axon.ui.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.UIEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.LedgerContainer;
import org.axonframework.examples.addressbook.vaadin.events.ActiveAccountDetailsRequestedEvent;
import org.axonframework.examples.addressbook.vaadin.events.LedgerModificationCompletedEvent;

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
