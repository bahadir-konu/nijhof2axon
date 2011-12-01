package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ClientDetailsEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-26
 * Time: 6:24:54 PM
 */
public class ChangeClientNameRequestedEvent implements MediatorEvent {
    private ClientDetailsEntry clientEntry;

    public ChangeClientNameRequestedEvent(ClientDetailsEntry clientEntry) {
        this.clientEntry = clientEntry;
    }

    public ClientDetailsEntry getClientEntry() {
        return clientEntry;
    }
}
