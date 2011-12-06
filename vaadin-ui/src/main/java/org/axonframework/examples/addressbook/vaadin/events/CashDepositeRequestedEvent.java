package org.axonframework.examples.addressbook.vaadin.events;

import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import nijhof2axon.app.query.ActiveAccountEntry;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class CashDepositeRequestedEvent implements MediatorEvent {

    ActiveAccountEntry activeAccountEntry;

    public CashDepositeRequestedEvent(ActiveAccountEntry activeAccountEntry) {
        this.activeAccountEntry = activeAccountEntry;
    }

    public ActiveAccountEntry getActiveAccountEntry() {
        return activeAccountEntry;
    }

    public void setActiveAccountEntry(ActiveAccountEntry activeAccountEntry) {
        this.activeAccountEntry = activeAccountEntry;
    }
}
