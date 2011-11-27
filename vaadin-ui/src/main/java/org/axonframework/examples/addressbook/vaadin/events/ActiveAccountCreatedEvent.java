package org.axonframework.examples.addressbook.vaadin.events;

import org.axonframework.examples.addressbook.vaadin.MediatorEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-25
 * Time: 12:46:26 PM
 */
public class ActiveAccountCreatedEvent implements MediatorEvent {

    private String clientIdentifier;

    public ActiveAccountCreatedEvent(String identifier) {
        this.clientIdentifier = identifier;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }
}
