package org.axonframework.sample.app.query;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.sample.app.api.fohjin.event.CashDepositedEvent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-05
 * Time: 12:49:17 PM
 */
public class LedgerTableUpdater {
    @PersistenceContext
    private EntityManager entityManager;

    @EventHandler
    public void handleCashDepositedEvent(CashDepositedEvent event) {
        LedgerEntry ledgerEntry = new LedgerEntry(event.getAmount(), "Deposit");

        entityManager.persist(ledgerEntry);
    }
}

