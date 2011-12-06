package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ClientDetailsEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class AddActiveAccountRequestedEvent implements MediatorEvent {
    private ClientDetailsEntry clientDetailsEntry;

    public AddActiveAccountRequestedEvent(ClientDetailsEntry clientDetailsEntry) {
        this.clientDetailsEntry = clientDetailsEntry;
    }

    public ClientDetailsEntry getClientDetailsEntry() {
        return clientDetailsEntry;
    }
}
