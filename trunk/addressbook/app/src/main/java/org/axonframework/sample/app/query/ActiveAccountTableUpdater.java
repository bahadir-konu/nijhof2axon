package org.axonframework.sample.app.query;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.sample.app.api.fohjin.event.ActiveAccountOpenedEvent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-01
 * Time: 5:46:22 PM
 */
public class ActiveAccountTableUpdater {
    @PersistenceContext
    private EntityManager entityManager;

    @EventHandler
    public void handleActiveAccountOpenedEvent(ActiveAccountOpenedEvent event) {
        ActiveAccountEntry activeAccountEntry = new ActiveAccountEntry();
        activeAccountEntry.setAccountName(event.getAccountName());
        activeAccountEntry.setIdentifier(event.getAggregateIdentifier().asString());

        entityManager.persist(activeAccountEntry);
    }
}

