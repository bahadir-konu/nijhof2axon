package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ActiveAccountEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-26
 * Time: 6:03:06 PM
 */
public class CashWithdrawalRequestedEvent implements MediatorEvent {

    private ActiveAccountEntry activeAccountEntry;

    public CashWithdrawalRequestedEvent(ActiveAccountEntry activeAccountEntry) {
        this.activeAccountEntry = activeAccountEntry;
    }

    public ActiveAccountEntry getActiveAccountEntry() {
        return activeAccountEntry;
    }

}
