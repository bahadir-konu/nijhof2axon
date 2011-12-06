package org.axonframework.examples.addressbook.vaadin.events;

import org.axonframework.examples.addressbook.vaadin.UIEvent;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ActiveAccountCreatedEvent implements UIEvent {

    private String clientIdentifier;

    public ActiveAccountCreatedEvent(String identifier) {
        this.clientIdentifier = identifier;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }
}
