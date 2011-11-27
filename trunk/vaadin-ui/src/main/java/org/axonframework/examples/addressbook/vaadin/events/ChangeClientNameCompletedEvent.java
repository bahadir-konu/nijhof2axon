package org.axonframework.examples.addressbook.vaadin.events;

import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.sample.app.query.ClientEntry;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-26
 * Time: 6:32:28 PM
 */
public class ChangeClientNameCompletedEvent implements MediatorEvent {
    private ClientEntry clientEntry;

    public ChangeClientNameCompletedEvent(ClientEntry clientEntry) {
        this.clientEntry = clientEntry;
    }

    public ClientEntry getClientEntry() {
        return clientEntry;
    }
}
