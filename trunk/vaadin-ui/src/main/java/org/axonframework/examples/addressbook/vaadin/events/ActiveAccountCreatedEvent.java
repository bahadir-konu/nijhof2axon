package org.axonframework.examples.addressbook.vaadin.events;

import nijhof2axon.ui.UIEvent;

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
