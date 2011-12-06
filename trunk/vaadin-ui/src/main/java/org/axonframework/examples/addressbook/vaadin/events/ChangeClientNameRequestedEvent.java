package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ClientDetailsEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ChangeClientNameRequestedEvent implements MediatorEvent {
    private ClientDetailsEntry clientDetailsEntry;

    public ChangeClientNameRequestedEvent(ClientDetailsEntry clientDetailsEntry) {
        this.clientDetailsEntry = clientDetailsEntry;
    }

    public ClientDetailsEntry getClientDetailsEntry() {
        return clientDetailsEntry;
    }
}
