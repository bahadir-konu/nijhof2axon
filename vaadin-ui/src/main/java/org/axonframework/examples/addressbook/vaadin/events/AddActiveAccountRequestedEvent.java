package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ClientEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-28
 * Time: 9:49:34 PM
 */
public class AddActiveAccountRequestedEvent implements MediatorEvent {
    private ClientEntry clientEntry;

    public AddActiveAccountRequestedEvent(ClientEntry clientEntry) {
        this.clientEntry = clientEntry;
    }

    public ClientEntry getClientEntry() {
        return clientEntry;
    }
}
