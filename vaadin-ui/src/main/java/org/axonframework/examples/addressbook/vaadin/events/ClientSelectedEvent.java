package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ClientDetailsEntry;
import org.axonframework.examples.addressbook.vaadin.UIEvent;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientSelectedEvent implements UIEvent {
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
