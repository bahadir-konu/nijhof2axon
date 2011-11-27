package org.axonframework.examples.addressbook.vaadin.events;

import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.sample.app.query.ActiveAccountEntry;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-26
 * Time: 5:59:32 PM
 */
public class CashDepositeCompletedEvent implements MediatorEvent {

    private ActiveAccountEntry activeAccountEntry;

    public CashDepositeCompletedEvent(ActiveAccountEntry activeAccountEntry) {
        this.activeAccountEntry = activeAccountEntry;
    }

    public ActiveAccountEntry getActiveAccountEntry() {
        return activeAccountEntry;
    }
}
