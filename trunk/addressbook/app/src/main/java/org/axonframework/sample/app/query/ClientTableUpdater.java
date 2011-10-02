package org.axonframework.sample.app.query;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.sample.app.api.fohjin.event.ClientCreatedEvent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-02
 * Time: 2:59:29 PM
 */
public class ClientTableUpdater {
    @PersistenceContext
    private EntityManager entityManager;

    @EventHandler
    public void handleClientCreatedEvent(ClientCreatedEvent event) {
        ClientEntry entry = new ClientEntry();
        entry.setIdentifier(event.getEventIdentifier());
        entry.setName(event.getName());
        entityManager.persist(entry);
    }


}

