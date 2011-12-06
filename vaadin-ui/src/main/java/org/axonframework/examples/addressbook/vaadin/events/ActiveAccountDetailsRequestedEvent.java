package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ActiveAccountEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ActiveAccountDetailsRequestedEvent implements MediatorEvent {

    private ActiveAccountEntry activeAccountEntry;

    public ActiveAccountDetailsRequestedEvent(ActiveAccountEntry activeAccountEntry) {
        this.activeAccountEntry = activeAccountEntry;
    }

    public ActiveAccountEntry getActiveAccountEntry() {
        return activeAccountEntry;
    }
}
