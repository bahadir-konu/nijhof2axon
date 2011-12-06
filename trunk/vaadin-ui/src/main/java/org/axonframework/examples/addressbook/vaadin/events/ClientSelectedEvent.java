package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ClientDetailsEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientSelectedEvent implements MediatorEvent {
    private ClientDetailsEntry selectedClient;

    public ClientSelectedEvent(ClientDetailsEntry clientEntry) {
        this.selectedClient = clientEntry;
    }

    public ClientDetailsEntry getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(ClientDetailsEntry selectedClient) {
        this.selectedClient = selectedClient;
    }
}
