package org.axonframework.examples.addressbook.vaadin.events;

import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.sample.app.query.ClientEntry;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-23
 * Time: 5:04:14 PM
 */
public class ClientSelectedEvent implements MediatorEvent {
    private ClientEntry selectedClient;

    public ClientSelectedEvent(ClientEntry clientEntry) {
        this.selectedClient = clientEntry;
    }

    public ClientEntry getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(ClientEntry selectedClient) {
        this.selectedClient = selectedClient;
    }
}
