package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ClientDetailsEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-28
 * Time: 9:49:34 PM
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
