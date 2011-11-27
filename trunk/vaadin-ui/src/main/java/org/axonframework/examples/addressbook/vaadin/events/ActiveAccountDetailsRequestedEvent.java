package org.axonframework.examples.addressbook.vaadin.events;

import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.sample.app.query.ActiveAccountEntry;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-25
 * Time: 12:56:26 PM
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
