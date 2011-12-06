package org.axonframework.examples.addressbook.vaadin.events;

import org.axonframework.examples.addressbook.vaadin.UIEvent;
import nijhof2axon.app.query.ActiveAccountEntry;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class LedgerModificationCompletedEvent implements UIEvent {

    private ActiveAccountEntry activeAccountEntry;

    public LedgerModificationCompletedEvent(ActiveAccountEntry activeAccountEntry) {
        this.activeAccountEntry = activeAccountEntry;
    }

    public ActiveAccountEntry getActiveAccountEntry() {
        return activeAccountEntry;
    }
}
