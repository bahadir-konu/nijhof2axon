package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.app.query.ClientDetailsEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-23
 * Time: 5:04:14 PM
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
