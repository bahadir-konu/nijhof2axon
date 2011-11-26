package org.axonframework.examples.addressbook.vaadin.events;

import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.sample.app.query.ClientEntry;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-26
 * Time: 6:24:54 PM
 */
public class ChangeClientNameRequestedEvent implements MediatorEvent {
    private ClientEntry clientEntry;

    public ChangeClientNameRequestedEvent(ClientEntry clientEntry) {
        this.clientEntry = clientEntry;
    }

    public ClientEntry getClientEntry() {
        return clientEntry;
    }
}
