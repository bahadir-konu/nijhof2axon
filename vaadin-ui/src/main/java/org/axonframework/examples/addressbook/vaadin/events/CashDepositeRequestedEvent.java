package org.axonframework.examples.addressbook.vaadin.events;

import org.axonframework.examples.addressbook.vaadin.UIEvent;
import nijhof2axon.app.query.ActiveAccountEntry;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class CashDepositeRequestedEvent implements UIEvent {

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
