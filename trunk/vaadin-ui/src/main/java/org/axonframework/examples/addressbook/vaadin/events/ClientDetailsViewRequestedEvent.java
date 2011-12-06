package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ClientEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientDetailsViewRequestedEvent implements MediatorEvent {
    private ClientEntry clientEntry;

    public ClientDetailsViewRequestedEvent(ClientEntry clientEntry) {
        this.clientEntry = clientEntry;
    }

    public ClientEntry getClientEntry() {
        return clientEntry;
    }

    public void setClientEntry(ClientEntry clientEntry) {
        this.clientEntry = clientEntry;
    }
}
